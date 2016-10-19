package model.command;

import model.exception.ArgumentException;
import model.robot.Turtle;

public class SetHeadingCommand extends RotationCommand {
	
	public SetHeadingCommand(Turtle aRobot) {
		super(aRobot);
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		double rotation = getRotation(aList);
		getRobot().setRotation(rotation);
		return Double.parseDouble(aList[0]);
	}

}