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
	
	public static void main(String[] args) {
		ModelController model = new ModelController();
		model.userInputToModel("sum 3 if [ fd 4 ] [ sum 3 3 ]\nfd 3");
	}
	
	public void userInputToModel(String aString) {
		TextParser parser = new TextParser(myScope, myRobot);
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

}
