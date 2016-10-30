package back_end.model.command;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.IModifiableVariableState;

import back_end.model.exception.InvalidNodeUsageException;
public class RetrieveValueCommand implements ICommand {
	private IModifiableVariableState myEnvironment;

    public RetrieveValueCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
    	myEnvironment = aEnvironment;
    }

	@Override
	public double eval(IReadableInput... aList) throws InvalidNodeUsageException {
		String variableName = aList[0].getName();

		return myEnvironment.getValue(variableName);
	}

}
