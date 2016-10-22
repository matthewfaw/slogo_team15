package model.command;


import model.exception.ArgumentException;
import model.robot.Robot;

public class ClearScreenCommand extends HomeCommand {

	public ClearScreenCommand(Robot aRobot) {
		super(aRobot);
	}
	
	@Override
	public double eval(String... aList) throws ArgumentException {
		return super.eval(aList);
	}

}
