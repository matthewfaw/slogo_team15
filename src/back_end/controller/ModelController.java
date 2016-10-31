package back_end.controller;

import java.lang.reflect.InvocationTargetException;
import back_end.model.exception.ArgumentException;
import back_end.model.exception.EmptyInputException;
import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.exception.UnexpectedCommandException;
import back_end.model.robot.IViewableRobot;
import back_end.model.robot.RobotController;
import back_end.model.robot.IRobot;
import back_end.model.robot.Turtle;
import back_end.model.states.Environment;
import back_end.model.states.IViewableVariableState;
import back_end.model.states.ScopeController;
import back_end.model.syntax_tree.AbstractSyntaxTree;
import back_end.model.syntax_tree.TreeEvaluator;
import back_end.model.text_parser.TextParser;
import integration.languages.Languages;
import integration.observe.IObservable;
import integration.observe.IObserver;
import integration.router.IRouter;


public class ModelController {
	
	private Environment myEnvironment; 
	private ScopeController myScopeController;
	private IRobot myRobot; 
	private TextParser myParser;
	private IRouter myRouter;
	
	public static void main(String[] args)
	{
		
	}

	public ModelController(IRouter aRobotRouter) {
		myRouter = aRobotRouter;
		myEnvironment = Environment.getInstance();
		myRobot = new RobotController();
		distributeRobot(myRobot);
		distributeVariableState(myEnvironment);
		myScopeController = new ScopeController();
		myParser = new TextParser(myScopeController, myEnvironment, (IRobot) myRobot);
	}
	
	public void userInputToModel(String aString) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, UnexpectedCharacterException, UnexpectedCommandException, EmptyInputException {
		AbstractSyntaxTree ast = new AbstractSyntaxTree(myParser.getNodeStack(aString));
		TreeEvaluator treeEvaluator = new TreeEvaluator(ast);
		try {
			while (treeEvaluator.hasNextInstruction()) {
				treeEvaluator.executeNextInstruction();
			}
		} catch (ArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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