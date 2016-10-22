package model.command;

import model.robot.Robot;

public class ShowTurtleCommand implements ICommand {
	
	private Robot myRobot;
	
	public ShowTurtleCommand(Robot aRobot) {
		super();
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) {
		myRobot.setVisible(true);
		return 1;
	}

}