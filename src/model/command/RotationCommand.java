package model.command;


import model.robot.Robot;

public abstract class RotationCommand implements ICommand {
	private Robot myRobot;
	
	public RotationCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	public double getRotation(String... aList) {
		double rotationValue = Double.parseDouble(aList[0]) % 360;
		if (rotationValue < 0) {
			rotationValue = 360 + rotationValue;
		}
		return rotationValue;
		
	}
	
}
