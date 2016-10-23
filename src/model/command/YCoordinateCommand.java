package model.command;

import model.node.IReadableInput;
import model.robot.Robot;

public class YCoordinateCommand implements ICommand {
	
	private Robot myRobot;
	
	public YCoordinateCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		return myRobot.getY();
	}

}
