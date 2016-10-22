package model.command;

import model.robot.Robot;

public class IsPenDownCommand implements ICommand {
	
	private Robot myRobot;
	
	public IsPenDownCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) {
		double returnVal = (myRobot.isPenDown()) ? 1 : 0;
		return returnVal;
	}

}
