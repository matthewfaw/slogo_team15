package model.command.turtleCommands;

import model.exception.ArgumentException;
import model.robot.Robot;

public class HideTurtleCommand extends BooleanTurtleCommand {
	
	private Robot myRobot;
	
	public HideTurtleCommand(Robot aRobot) {
		super();
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		errorCheck(aList);
		myRobot.setVisible(false);
		return 0;
	}

}