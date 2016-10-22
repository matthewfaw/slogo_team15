package model.command;

import model.robot.Robot;

public class IsShowingCommand implements ICommand {
	
	private Robot myRobot;
	
	public IsShowingCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) {
		double returnVal = (myRobot.isVisible()) ? 1 : 0;
		return returnVal;
	}

}
