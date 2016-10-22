package model.command;

import model.exception.ArgumentException;
import model.robot.Robot;
import model.robot.Turtle;

public class PenDownCommand extends BooleanTurtleCommand {
	
	private Robot myRobot;
	
	public PenDownCommand(Robot aRobot) {
		super();
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		errorCheck(aList);
		myRobot.setPenDown(true);
		return 1;
	}

}
