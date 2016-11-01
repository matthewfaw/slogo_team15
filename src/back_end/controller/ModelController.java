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
import back_end.model.states.IViewableVariableState;
import back_end.model.states.ScopeController;
import back_end.model.states.background.IViewableBackground;
import back_end.model.states.methodhistory.IViewableUserInputHistory;
import back_end.model.states.methodhistory.UserInputHistory;
import back_end.model.syntax_tree.AbstractSyntaxTree;
import back_end.model.syntax_tree.TreeEvaluator;
import back_end.model.text_parser.TextParser;
import integration.languages.ILanguageSwitcher.Languages;
import integration.router.IRouter;


public class ModelController {
	
	private Environment myEnvironment; 
	private ScopeController myScopeController;
	private IRobot myRobot; 
	private TextParser myParser;
	private IRouter myRouter;
	private IViewableBackground myBackgroundInformation;
	private IViewableUserInputHistory myUserInputHistory; 
	
//	public static void main(String[] args) throws InvalidNodeUsageException
//	{
//		Environment environment = Environment.getInstance();
//		IRobot robot = new RobotController();
//		ScopeController scopeController = new ScopeController();
//		TextParser textParser = new TextParser(scopeController, environment, robot);
//
//		String aString = "if [ make :x 5 ] [ to :haha [ :a ] [ fd :a ] ] haha [ fd 50 ]";
//		
//		
//		AbstractSyntaxTree ast;
//		try {
//			ast = new AbstractSyntaxTree(textParser.getNodeStack(aString));
//			TreeEvaluator treeEvaluator = new TreeEvaluator(ast);
//			try {
//				while (treeEvaluator.hasNextInstruction()) {
//					treeEvaluator.executeNextInstruction();
//				}
//			} catch (InvalidInputNumberException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
//				| NoSuchMethodException | SecurityException | ClassNotFoundException | UnexpectedCharacterException
//				| UnexpectedCommandException | EmptyInputException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}

	public ModelController(IRouter aRobotRouter) {
		myRouter = aRobotRouter;
		myEnvironment = Environment.getInstance();
		myRobot = new RobotController();
		myBackgroundInformation = myEnvironment.getBackgroundInformation();
		myUserInputHistory = new UserInputHistory();
		distributeRobot(myRobot.getMostRecentRobot());
		distributeVariableState(myEnvironment);
		distributeBackgroundInformation(myBackgroundInformation);
		distributeUserInputHistory(myUserInputHistory);
		myScopeController = new ScopeController();
		myParser = new TextParser(myScopeController, myEnvironment, (IRobot) myRobot);
	}
	
	public void userInputToModel(String aString) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, UnexpectedCharacterException, UnexpectedCommandException, EmptyInputException, InvalidNodeUsageException {
		AbstractSyntaxTree ast = new AbstractSyntaxTree(myParser.getNodeStack(aString));
		TreeEvaluator treeEvaluator = new TreeEvaluator(ast);
		try {
			while (treeEvaluator.hasNextInstruction()) {
				treeEvaluator.executeNextInstruction();
			}
		} catch (InvalidInputNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void distributeUserInputHistory( IViewableUserInputHistory aUserInputHistory ) {
		//myRouter.distributeUserInputHistory(aUserInputHistory);
	}
	
	private void distributeBackgroundInformation( IViewableBackground aBackgroundInformation ) {
		//myRouter.distributeBackgroundInformation(aBackgroundInformation);
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
	
}
