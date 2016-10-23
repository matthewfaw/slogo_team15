package model.command;

import model.node.IReadableInput;
import model.robot.Robot;

public class PenUpCommand implements ICommand {
	
	private Robot myRobot;
	
	public PenUpCommand(Robot aRobot) {
		super();
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		myRobot.setPenDown(false);
		return 0;
	}

}
