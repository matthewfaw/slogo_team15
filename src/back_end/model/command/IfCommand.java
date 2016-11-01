package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;

public class IfCommand extends ICommandBranch {

    private boolean myFirstExecution;

    public IfCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myFirstExecution = true;
    }

	@Override
	protected int evalConditionInNode(IReadableInput... aList) throws InvalidNodeUsageException, InvalidInputNumberException {
		errorCheckForTooManyInputs(aList.length, 2);
		if (myFirstExecution) {
			myFirstExecution = false;
			return (aList[0].getValue() != 0) ? 0 : -1;
		}
		return -1;
	}

}
