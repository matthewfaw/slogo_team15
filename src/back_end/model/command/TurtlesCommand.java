package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.Environment;
import back_end.model.states.IModifiableEnvironmentState;

public class TurtlesCommand implements ICommand {
	
	private IModifiableEnvironmentState myEnvironment;
	
	public TurtlesCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myEnvironment = aEnvironment;
	}

	@Override
	public double eval(IReadableInput... aList) {
		return myEnvironment.numberOfTurtlesCreated();
	}

}
