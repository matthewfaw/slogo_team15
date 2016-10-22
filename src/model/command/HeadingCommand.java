package model.command;

import model.robot.Robot;

public class HeadingCommand implements ICommand {
	
	private Robot myRobot;
	
	public HeadingCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) {
		return myRobot.getRotation();
	}

}
