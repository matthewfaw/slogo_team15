package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class SetBackgroundCommand implements ICommand {
	
	private IModifiableEnvironmentState myEnvironment; 

	public SetBackgroundCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myEnvironment = aEnvironment;
	}

	@Override
	public double eval(IReadableInput... aList) {
		myEnvironment.setBackgroundColor((int) aList[0].getValue());
		return (double) aList[0].getValue();
	}
	
	
	
}
