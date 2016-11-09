package back_end.controller;

import back_end.model.exception.EmptyInputException;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.exception.UnexpectedCommandException;
import back_end.model.robot.IViewableRobot;
import back_end.model.robot.RobotController;
import back_end.model.robot.Turtle;
import back_end.model.states.Environment;
import back_end.model.states.IViewableMethodState;
import back_end.model.states.IViewableVariableState;
import back_end.model.states.ScopeController;
import back_end.model.states.background.BackgroundInformation;
import back_end.model.states.background.IViewableBackground;
import back_end.model.states.background.IViewableColorPalette;
import back_end.model.states.methodhistory.IViewableUserInputHistory;
import back_end.model.states.methodhistory.UserInputHistory;
import back_end.model.syntax_tree.AbstractSyntaxTree;
import back_end.model.syntax_tree.TreeEvaluator;
import back_end.model.text_parser.TextParser;
import front_end.sender.IColorSender;
import front_end.sender.IRobotSender;
import integration.drawing.PenInformation;
import integration.languages.ILanguageSwitcher.Languages;
import integration.observe.IObserver;
import integration.router.IRouter;

/**
 * The Controller for the back-end. Provides a centralized class for all creation of objects that persist through the program 
 * and also allows for easy distribution of all backend information to the frontend and vice versa. 
 *
 * @author Hannah Fuchshuber and matthewfaw
 */

public class ModelController implements IObserver, IColorSender, IRobotSender {
	
	private Environment myEnvironment; 
	private ScopeController myScopeController;
	private RobotController myRobot; 
	private TextParser myParser;
	private IRouter myRouter;
	private BackgroundInformation myBackgroundInformation;
	private UserInputHistory myUserInputHistory; 
	
	private TreeEvaluator myTreeEval;
	
	public ModelController(IRouter aRobotRouter) {
		myRouter = aRobotRouter;

		reset();
	}
	
	/**
	 * When the user inputs a String to be evaluated this function is called 
	 * 
	 * @param aString
	 */
	public void inputAll(String aString) {
		try {
			makeSyntaxTreeEvaluator(aString);
			if(myTreeEval == null) return;
			while (myTreeEval.hasNextInstruction()) {
				myTreeEval.executeNextInstruction();
			}
		} catch (InvalidNodeUsageException |  InvalidInputNumberException e) {
				myRouter.distributeError(e);
		}
	}
	
	/**
	 * Creates the evaluator for the AST
	 * @param aString
	 */
	public void makeSyntaxTreeEvaluator(String aString) {
		AbstractSyntaxTree ast;
		try {
			ast = new AbstractSyntaxTree(myParser.getNodeStack(aString));
			myTreeEval = new TreeEvaluator(ast);
		} catch (InvalidNodeUsageException | EmptyInputException | UnexpectedCharacterException
				| UnexpectedCommandException e) {
			myRouter.distributeError(e);
		}
		myUserInputHistory.storeMethod(aString);
	}
	
	/**
	 * Checks if there is a valid next instruction in the tree
	 * @return
	 */
	public boolean canStep() {
		if(myTreeEval == null) return false;
		try {
			return myTreeEval.hasNextInstruction();
		} catch (InvalidNodeUsageException e) {
			myRouter.distributeError(e);
		}
		return false;
	}
	
	/**
	 * Steps through the AST instruction by instruction
	 */
	public void stepInstruction(){
		if(myTreeEval == null) return;
		try {
			if(myTreeEval.hasNextInstruction()){
				myTreeEval.executeNextInstruction();
			}
		} catch (InvalidNodeUsageException | InvalidInputNumberException e) {
			myRouter.distributeError(e);
		}
	}
	
	/**SETTERS TO TALK TO FRONT **/
	@Override
	public void setPenInformation(int aTurtleID, PenInformation aPenInfo) {
		Turtle requestedRobot = myRobot.getTurtle(aTurtleID);
		requestedRobot.setPenInformation(aPenInfo);
	}
	
	@Override
	public void newColor(String aRGB) {
		myBackgroundInformation.newPaletteColor(aRGB);
	}

	@Override
	public void setColor(int aColorID, String aHexadecimal) {
		myBackgroundInformation.setPaletteColor(aColorID, aHexadecimal);		
	}

	@Override
	public void setVisibility(int aID, boolean aVisibility) {
		Turtle turtle = myRobot.getTurtle(aID);
		turtle.setVisible(aVisibility);
	}
	
	@Override
	public void setBackground(int aColorID) {
		myBackgroundInformation.setBackgroundColor(aColorID);
	}
	
	@Override
	public void setBackground(String aHexadecimal) {
		myBackgroundInformation.setBackgroundColor(aHexadecimal);
	}
	
	/**
	 * This method distributes the different backend components to the front through the Router 
	 */
	public void reset() {
		myRobot = new RobotController();
		myEnvironment = new Environment(myRobot);
		myRobot.registerObserver(this);
		myBackgroundInformation = myEnvironment.getBackgroundInformation();
		myUserInputHistory = new UserInputHistory();
		myRouter.distributeColorSenders(this);
		myRouter.distributeRobotSenders(this); // MUST HAPPEN BEFORE ROBOT DISTRIBUTION
		distributeRobot(myRobot.getMostRecentRobot());
		distributeVariableState(myEnvironment);
		distributeBackground(myBackgroundInformation);
		distributeColorPalette(myBackgroundInformation);
		distributeHistory(myUserInputHistory);
		distributeMethods(myEnvironment);
		myScopeController = new ScopeController(myEnvironment, myRobot);
		myParser = new TextParser(myScopeController, myEnvironment, myRobot);
	}

	
	/** DISTRIBUTE TO ROUTER **/
	
	private void distributeMethods( IViewableMethodState aMethodState ) {
	    myRouter.distributeMethods(aMethodState);
	}
	
	private void distributeHistory( IViewableUserInputHistory aUserInputHistory ) {
		myRouter.distributeHistory(aUserInputHistory);
	}
	
	private void distributeBackground( IViewableBackground aBackgroundInformation ) {
		myRouter.distributeBackground(aBackgroundInformation);
	}
	
	private void distributeColorPalette( IViewableColorPalette aColorPalette ) {
		myRouter.distributeColorPalette(aColorPalette);
	}
			
	private void distributeRobot( IViewableRobot aRobot ){
		myRouter.distributeRobot( aRobot );
	}
	
	private void distributeVariableState (IViewableVariableState aViewableVariableState){
		myRouter.distributeVariableMap(aViewableVariableState);
	}
	
	public void setLanguage(Languages aLanguage) {
		myParser.setLanguage(aLanguage);
	}

	@Override
	public void update() {
		myRouter.distributeRobot(myRobot.getMostRecentRobot());
	}

}
