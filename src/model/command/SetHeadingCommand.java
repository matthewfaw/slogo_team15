package model.command;

import model.exception.ArgumentException;
import model.robot.Turtle;

public class SetHeadingCommand extends RotationCommand {
	
	public SetHeadingCommand(Turtle aRobot) {
		super(aRobot);
	}

	@Override
	public void eval(String... aList) throws ArgumentException {
		double rotation = getRotation(aList);
		getRobot().setRotation(rotation);
	}

}