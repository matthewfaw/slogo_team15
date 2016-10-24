package model.command;

import model.node.IReadableInput;
import model.robot.Robot;

public class LeftCommand extends RotationCommand {
	
	private Robot myRobot;
	
	public LeftCommand(Robot aRobot) {
		super(aRobot);
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		double rotation = getRotation(aList);
		myRobot.setRotation(myRobot.getRotation() + rotation);
		return aList[0].getValue();
	}

}
