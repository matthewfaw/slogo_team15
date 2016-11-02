package back_end.controller;

import java.lang.reflect.InvocationTargetException;

import back_end.model.exception.EmptyInputException;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.exception.UnexpectedCommandException;
import back_end.model.robot.IRobot;
import back_end.model.robot.IViewableRobot;
import back_end.model.robot.RobotController;
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
	
	/*public static void main(String[] args) throws InvalidNodeUsageException
	{
		Environment environment = new Environment();
		RobotController robot = new RobotController();
		ScopeController scopeController = new ScopeController(environment, robot);
		TextParser textParser = new TextParser(scopeController, environment, robot);

//		String aString = "if [ make :x 5 ] [ to :haha [ :a ] [ fd :a ] ] haha [ fd 50 ]";
//		String aString = "to :derp [ :a :b ] [ make :c :a make :d :b ] derp [ 13 56 ]";
//		String aString = "showturtle";
		String aString = "fd 50";
		
		
		AbstractSyntaxTree ast;
		try {
			ast = new AbstractSyntaxTree(textParser.getNodeStack(aString));
			TreeEvaluator treeEvaluator = new TreeEvaluator(ast);
			try {
				while (treeEvaluator.hasNextInstruction()) {
					treeEvaluator.executeNextInstruction();
				}
			} catch (InvalidInputNumberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException | UnexpectedCharacterException
				| UnexpectedCommandException | EmptyInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}*/

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
		distributeMethods(myEnvironment);
		myScopeController = new ScopeController(myEnvironment, myRobot);
		myParser = new TextParser(myScopeController, myEnvironment, myRobot);
	}
	
	public void userInputToModel(String aString) {
		AbstractSyntaxTree ast;
		try {
			ast = new AbstractSyntaxTree(myParser.getNodeStack(aString));
			TreeEvaluator treeEvaluator = new TreeEvaluator(ast);
			while (treeEvaluator.hasNextInstruction()) {
				treeEvaluator.executeNextInstruction();
			}
			myUserInputHistory.storeMethod(aString);
		} catch (InvalidNodeUsageException | EmptyInputException | UnexpectedCharacterException
				| UnexpectedCommandException | InvalidInputNumberException e) {
				myRouter.distributeError(e);
		}
		//myUserInputHistory.storeMethod(aString);
	}
	
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
