package model.command;

import model.exception.ArgumentException;
import model.robot.Robot;

public class YCoordinateCommand extends QueryCommand {
	
	private Robot myRobot;
	
	public YCoordinateCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		errorCheck();
		return myRobot.getY();
	}

}
