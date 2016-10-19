package model.command.turtleCommands;


import model.command.ICommand;
import model.exception.ArgumentException;
import model.robot.Robot;

public abstract class RotationCommand implements ICommand {
	private Robot myRobot;
	
	public RotationCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	public double getRotation(String... aList) throws ArgumentException {
		if (aList.length != 1) {
			throw new ArgumentException("Method Evaluation needs one argument");
		}
		double rotationValue = Double.parseDouble(aList[0]) % 360;
		if (rotationValue < 0) {
			rotationValue = 360 + rotationValue;
		}
		return rotationValue;
		
	}
	
	public Robot getRobot() {
		return myRobot;
	}
}
