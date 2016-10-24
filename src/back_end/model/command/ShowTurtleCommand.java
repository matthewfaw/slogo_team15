package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;

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