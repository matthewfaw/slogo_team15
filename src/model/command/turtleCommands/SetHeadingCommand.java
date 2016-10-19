package model.command.turtleCommands;

import model.exception.ArgumentException;
import model.robot.Robot;

public class SetHeadingCommand extends RotationCommand {
	
	public SetHeadingCommand(Robot aRobot) {
		super(aRobot);
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		double rotation = getRotation(aList);
		double returnVal = Math.abs(getRobot().getRotation() - rotation);
		getRobot().setRotation(rotation);
		return returnVal;
	}

}