package model.command.turtleCommands;

import model.exception.ArgumentException;
import model.robot.Robot;

public class ShowTurtleCommand extends BooleanTurtleCommand {
	
	private Robot myRobot;
	
	public ShowTurtleCommand(Robot aRobot) {
		super();
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		errorCheck(aList);
		myRobot.setVisible(true);
		return 1;
	}

}