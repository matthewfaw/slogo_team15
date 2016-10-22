package model.command;

import model.exception.ArgumentException;
import model.robot.Robot;
import model.robot.Turtle;

public class PenUpCommand extends BooleanTurtleCommand {
	
	private Robot myRobot;
	
	public PenUpCommand(Robot aRobot) {
		super();
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		errorCheck(aList);
		myRobot.setPenDown(false);
		return 0;
	}

}
