package model.command;

import model.robot.Robot;

public class YCoordinateCommand implements ICommand {
	
	private Robot myRobot;
	
	public YCoordinateCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) {
		return myRobot.getY();
	}

}
