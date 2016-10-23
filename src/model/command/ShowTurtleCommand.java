package model.command;

import model.node.IReadableInput;
import model.robot.Robot;

public class ShowTurtleCommand implements ICommand {
	
	private Robot myRobot;
	
	public ShowTurtleCommand(Robot aRobot) {
		super();
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		myRobot.setVisible(true);
		return 1;
	}

}