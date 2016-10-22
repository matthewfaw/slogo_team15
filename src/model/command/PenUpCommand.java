package model.command;

import model.robot.Robot;

public class PenUpCommand implements ICommand {
	
	private Robot myRobot;
	
	public PenUpCommand(Robot aRobot) {
		super();
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) {
		myRobot.setPenDown(false);
		return 0;
	}

}
