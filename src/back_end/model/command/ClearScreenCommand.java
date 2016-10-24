package back_end.model.command;


import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;

public class ClearScreenCommand extends HomeCommand {

	public ClearScreenCommand(Robot aRobot) {
		super(aRobot);
	}
	
	@Override
	public double eval(IReadableInput... aList) {
		return super.eval(aList);
	}

}
