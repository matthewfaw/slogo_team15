package model.command;

import model.node.IReadableInput;
import model.robot.Robot;

public class PenDownCommand implements ICommand {
	
	private Robot myRobot;
	
	public PenDownCommand(Robot aRobot) {
		super();
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		myRobot.setPenDown(true);
		return 1;
	}

}
