package back_end.model.command;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class RetrieveValueCommand implements ICommand {
	private IModifiableEnvironmentState myEnvironment;

    public RetrieveValueCommand(IRobot aRobot, IModifiableEnvironmentState  aEnvironment, String aCommandName) {
    	myEnvironment = aEnvironment;
    }

	@Override
	public double eval(IReadableInput... aList) throws InvalidNodeUsageException {
		String variableName = aList[0].getName();

		return myEnvironment.getValue(variableName);
	}

}
