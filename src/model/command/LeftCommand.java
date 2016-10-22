package model.command;

import model.robot.Robot;

public class LeftCommand extends RotationCommand {
	
	private Robot myRobot;
	
	public LeftCommand(Robot aRobot) {
		super(aRobot);
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) {
		double rotation = getRotation(aList);
		myRobot.setRotation(myRobot.getRotation() + rotation);
		myRobot.notifyObservers();
		return Double.parseDouble(aList[0]);
	}

}
