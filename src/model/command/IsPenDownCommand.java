package model.command;

import model.node.IReadableInput;
import model.robot.Robot;

public class IsPenDownCommand implements ICommand {
	
	private Robot myRobot;
	
	public IsPenDownCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		double returnVal = (myRobot.isPenDown()) ? 1 : 0;
		return returnVal;
	}

}
