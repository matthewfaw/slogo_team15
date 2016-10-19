package model.command.turtleQueries;

import model.exception.ArgumentException;
import model.robot.Robot;

public class IsShowingCommand extends QueryCommand {
	
	private Robot myRobot;
	
	public IsShowingCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		errorCheck();
		double returnVal = (myRobot.isVisible()) ? 1 : 0;
		return returnVal;
	}

}
