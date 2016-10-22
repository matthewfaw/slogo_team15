package model.command;

import model.exception.ArgumentException;
import model.robot.Robot;

public class XCoordinateCommand extends QueryCommand {
	
	private Robot myRobot;
	
	public XCoordinateCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		errorCheck();
		return myRobot.getX();
	}

}
