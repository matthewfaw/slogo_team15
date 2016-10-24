package back_end.controller;


import java.lang.reflect.InvocationTargetException;

import back_end.model.exception.ArgumentException;
import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.exception.UnexpectedCommandException;
import back_end.model.robot.Robot;
import back_end.model.robot.Turtle;
import back_end.model.states.IViewVariableState;
import back_end.model.states.Scope;
import back_end.model.syntax_tree.AbstractSyntaxTree;
import back_end.model.textParser.TextParser;
import integration.observe.IObservable;
import integration.observe.IObserver;

public class ModelController {
	
	private Scope myScope; 
	private IObservable myRobot; 
	
	public ModelController() {
		myScope = new Scope();
		myRobot = new Turtle();
	}
	
	public void userInputToModel(String aString) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, UnexpectedCharacterException, UnexpectedCommandException {
		TextParser parser = new TextParser(myScope, (Robot) myRobot);
		AbstractSyntaxTree ast = new AbstractSyntaxTree(parser.getNodeStack(aString));
		try {
			ast.executeNextInstruction();
		} catch (ArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void giveRobotObservers(IObserver ro){
		myRobot.registerObserver(ro);
	}
	
	public void giveVariableObservers(IObserver ro) {
		myScope.registerObserver(ro);
	}
	
	public IViewVariableState getVariableMap() {
		return myScope.getVariableMap();
	}
	

}
