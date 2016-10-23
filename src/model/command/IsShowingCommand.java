package model.command;

import model.node.IReadableInput;
import model.robot.Robot;

public class IsShowingCommand implements ICommand {
	
	private Robot myRobot;
	
	public IsShowingCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		double returnVal = (myRobot.isVisible()) ? 1 : 0;
		return returnVal;
	}

}
