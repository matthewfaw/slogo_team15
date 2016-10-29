package back_end.controller;

import java.lang.reflect.InvocationTargetException;
import back_end.model.exception.ArgumentException;
import back_end.model.exception.EmptyInputException;
import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.exception.UnexpectedCommandException;
import back_end.model.robot.IViewRobot;
import back_end.model.robot.Robot;
import back_end.model.robot.Turtle;
import back_end.model.states.Environment;
import back_end.model.states.IViewableVariableState;
import back_end.model.syntax_tree.AbstractSyntaxTree;
import back_end.model.text_parser.TextParser;
import integration.languages.Languages;
import integration.observe.IObservable;
import integration.observe.IRobotObserver;


public class ModelController {
	
	private Environment myEnvironment; 
	private IObservable myRobot; 
	private TextParser myParser;
	
	public ModelController() {
		myEnvironment = Environment.getInstance();
		myRobot = new Turtle();
		myParser = new TextParser(myEnvironment, (Robot) myRobot);
	}
	
	public void userInputToModel(String aString) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, UnexpectedCharacterException, UnexpectedCommandException, EmptyInputException {
		AbstractSyntaxTree ast = new AbstractSyntaxTree(myParser.getNodeStack(aString));
		try {
			while (ast.hasNextInstruction()) {
				ast.executeNextInstruction();
			}
		} catch (ArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void giveRobotObservers(IRobotObserver ro){
		myRobot.registerObserver(ro);
	}
	
	public void giveVariableObservers(IRobotObserver ro) {
		myEnvironment.registerObserver(ro);
	}
	
	//public IViewableVariableState getVariableMap() {
		//return myEnvironment.
	//}
	
	public IViewRobot giveRobotView() {
		return (IViewRobot) myRobot;
	}
	
	public void setLanguage(Languages aLanguage) {
		myParser.setLanguage(aLanguage);
	}
	
}
