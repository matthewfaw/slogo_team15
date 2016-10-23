package model.command;

import model.node.IReadableInput;
import model.robot.Robot;

public class XCoordinateCommand implements ICommand {
	
	private Robot myRobot;
	
	public XCoordinateCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		return myRobot.getX();
	}

}
