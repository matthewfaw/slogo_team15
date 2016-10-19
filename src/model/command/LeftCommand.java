package model.command;

import model.exception.ArgumentException;
import model.robot.Turtle;

public class LeftCommand extends RotationCommand {
	
	public LeftCommand(Turtle aRobot) {
		super(aRobot);
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		double rotation = getRotation(aList);
		getRobot().setRotation(getRobot().getRotation() + rotation);
		return Double.parseDouble(aList[0]);
	}

}
