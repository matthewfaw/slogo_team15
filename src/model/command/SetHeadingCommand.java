package model.command;

import model.robot.Robot;

public class SetHeadingCommand extends RotationCommand {
	
	private Robot myRobot;
	
	public SetHeadingCommand(Robot aRobot) {
		super(aRobot);
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) {
		double rotation = getRotation(aList);
		double returnVal = Math.abs(myRobot.getRotation() - rotation);
		myRobot.setRotation(rotation);
		myRobot.notifyObservers();
		return returnVal;
	}

}