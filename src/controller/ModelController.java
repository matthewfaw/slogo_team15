package controller;

import model.exception.ArgumentException;
import model.robot.Robot;
import model.robot.Turtle;
import model.states.Scope;
import model.syntax_tree.AbstractSyntaxTree;
import model.textParser.TextParser;

public class ModelController {
	
	private Scope myScope; 
	private Robot myRobot; 
	
	public ModelController() {
		myScope = new Scope();
		myRobot = new Turtle();
	}
	
	public void userInputToModel(String aString) {
		TextParser parser = new TextParser(myScope, myRobot);
		AbstractSyntaxTree ast = new AbstractSyntaxTree(parser.getNodeStack(aString));
		try {
			ast.executeNextInstruction();
		} catch (ArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
