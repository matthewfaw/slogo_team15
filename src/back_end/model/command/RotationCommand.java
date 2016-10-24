package back_end.model.command;


import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;

public abstract class RotationCommand implements ICommand {
	private Robot myRobot;
	
	public RotationCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	public double getRotation(IReadableInput... aList) {
		double rotationValue = aList[0].getValue() % 360;
		if (rotationValue < 0) {
			rotationValue = 360 + rotationValue;
		}
		return rotationValue;
		
	}
	
}
