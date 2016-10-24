package controller;

import integration.IRobotObserver;
import model.exception.ArgumentException;
import model.robot.Observable;
import model.robot.Robot;
import model.robot.Turtle;
import model.states.Scope;
import model.syntax_tree.AbstractSyntaxTree;
import model.textParser.TextParser;

public class ModelController {
	
	private Scope myScope; 
	private Observable myRobot; 
	
	public ModelController() {
		myScope = new Scope();
		myRobot = new Turtle();
	}
	
	public static void main(String[] args)
	{
		ModelController mc = new ModelController();
		String command = "+ 3 repeat + 2 3 [ + 3 3 ] \nfd 3";
		mc.userInputToModel(command);
	}
	
	public void userInputToModel(String aString) {
		TextParser parser = new TextParser(myScope, (Robot) myRobot);
		AbstractSyntaxTree ast = new AbstractSyntaxTree(parser.getNodeStack(aString));
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

}
