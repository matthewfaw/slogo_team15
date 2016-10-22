package model.command;

import model.robot.Robot;

public class RightCommand extends RotationCommand {
	
	private Robot myRobot;
	
	public RightCommand(Robot aRobot) {
		super(aRobot);
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) {
		double rotation = getRotation(aList);
		myRobot.setRotation(myRobot.getRotation() - rotation);
		return Double.parseDouble(aList[0]);
	}

}