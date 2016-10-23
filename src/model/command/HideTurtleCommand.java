package model.command;

import model.node.IReadableInput;
import model.robot.Robot;

public class HideTurtleCommand implements ICommand {
	
	private Robot myRobot;
	
	public HideTurtleCommand(Robot aRobot) {
		super();
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		myRobot.setVisible(false);
		return 0;
	}

}