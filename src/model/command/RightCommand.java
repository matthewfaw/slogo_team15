package model.command;

import model.exception.ArgumentException;
import model.robot.Robot;
import model.robot.Turtle;

public class RightCommand extends RotationCommand {
	
	public RightCommand(Robot aRobot) {
		super(aRobot);
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		double rotation = getRotation(aList);
		getRobot().setRotation(getRobot().getRotation() - rotation);
		return Double.parseDouble(aList[0]);
	}

}