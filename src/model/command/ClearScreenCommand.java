package model.command;


import model.robot.Robot;

public class ClearScreenCommand extends HomeCommand {

	public ClearScreenCommand(Robot aRobot) {
		super(aRobot);
	}
	
	@Override
	public double eval(String... aList) {
		return super.eval(aList);
	}

}
