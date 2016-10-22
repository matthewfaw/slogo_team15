package model.command;

import model.robot.Robot;

public class HideTurtleCommand implements ICommand {
	
	private Robot myRobot;
	
	public HideTurtleCommand(Robot aRobot) {
		super();
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) {
		myRobot.setVisible(false);
		myRobot.notifyObservers();
		return 0;
	}

}