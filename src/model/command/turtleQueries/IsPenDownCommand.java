package model.command.turtleQueries;

import model.exception.ArgumentException;
import model.robot.Robot;

public class IsPenDownCommand extends QueryCommand {
	
	private Robot myRobot;
	
	public IsPenDownCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		errorCheck();
		double returnVal = (myRobot.isPenDown()) ? 1 : 0;
		return returnVal;
	}

}
