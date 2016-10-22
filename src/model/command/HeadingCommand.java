package model.command;

import model.exception.ArgumentException;
import model.robot.Robot;

public class HeadingCommand extends QueryCommand {
	
	private Robot myRobot;
	
	public HeadingCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		errorCheck();
		return myRobot.getRotation();
	}

}
