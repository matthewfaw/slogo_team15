package back_end.controller;


import java.lang.reflect.InvocationTargetException;

import back_end.model.exception.ArgumentException;
import back_end.model.exception.EmptyInputException;
import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.exception.UnexpectedCommandException;
import back_end.model.robot.Robot;
import back_end.model.robot.Turtle;
import back_end.model.states.IViewVariableState;
import back_end.model.states.Scope;
import back_end.model.syntax_tree.AbstractSyntaxTree;
import back_end.model.text_parser.TextParser;
import integration.languages.Languages;
import integration.observe.IObservable;
import integration.observe.IRobotObserver;

public class ModelController {
	
	private Scope myScope; 
	private IObservable myRobot; 
	private TextParser myParser;
	
	public ModelController() {
		myScope = new Scope();
		myRobot = new Turtle();
		myParser = new TextParser(myScope, (Robot) myRobot);
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
		myScope.registerObserver(ro);
	}
	
	public IViewVariableState getVariableMap() {
		return myScope.getVariableMap();
	}
	
	public void setLanguage(Languages aLanguage) {
		myParser.setLanguage(aLanguage);
	}
	

}
