package back_end.controller;

import back_end.model.exception.EmptyInputException;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.exception.UnexpectedCommandException;
import back_end.model.robot.IViewableRobot;
import back_end.model.robot.RobotController;
import back_end.model.states.Environment;
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
import integration.languages.ILanguageSwitcher.Languages;
import integration.observe.IObserver;
import integration.router.IRouter;


public class ModelController implements IObserver {
	
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
		myRobot = new RobotController();
		myEnvironment = new Environment(myRobot);
		myRobot.registerObserver(this);
		myBackgroundInformation = myEnvironment.getBackgroundInformation();
		myUserInputHistory = new UserInputHistory();
		distributeRobot(myRobot.getMostRecentRobot());
		distributeVariableState(myEnvironment);
		distributeBackground(myBackgroundInformation);
		distributeColorPalette(myBackgroundInformation);
		distributeHistory(myUserInputHistory);
		myScopeController = new ScopeController(myEnvironment, myRobot);
		myParser = new TextParser(myScopeController, myEnvironment, myRobot);
	}
	
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
	
	public boolean canStep() {
		if(myTreeEval == null) return false;
		try {
			return myTreeEval.hasNextInstruction();
		} catch (InvalidNodeUsageException e) {
			myRouter.distributeError(e);
		}
		return false;
	}
	
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
