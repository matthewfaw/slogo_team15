package controller;

import model.robot.Robot;
import model.robot.Turtle;
import model.states.Scope;
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
		parser.createNodes(aString);
	}

}
