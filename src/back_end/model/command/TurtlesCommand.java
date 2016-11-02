package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class TurtlesCommand implements ICommand, ICommandTurtle {
	
	private IRobot myRobot;
	
	public TurtlesCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		return myRobot.getNumberOfTurtles();
	}

}
