package model.command;


import model.node.IReadableInput;
import model.robot.Robot;

public class ClearScreenCommand extends HomeCommand {

	public ClearScreenCommand(Robot aRobot) {
		super(aRobot);
	}
	
	@Override
	public double eval(IReadableInput... aList) {
		return super.eval(aList);
	}

}
