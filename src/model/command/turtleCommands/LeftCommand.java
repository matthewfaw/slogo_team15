package model.command.turtleCommands;

import model.exception.ArgumentException;
import model.robot.Robot;

public class LeftCommand extends RotationCommand {
	
	public LeftCommand(Robot aRobot) {
		super(aRobot);
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		double rotation = getRotation(aList);
		getRobot().setRotation(getRobot().getRotation() + rotation);
		return Double.parseDouble(aList[0]);
	}

}
