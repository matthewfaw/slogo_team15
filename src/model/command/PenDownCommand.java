package model.command;

import model.robot.Robot;

public class PenDownCommand implements ICommand {
	
	private Robot myRobot;
	
	public PenDownCommand(Robot aRobot) {
		super();
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) {
		myRobot.setPenDown(true);
		return 1;
	}

}
