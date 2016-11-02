package back_end.model.command;

import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class AskWithCommand extends ICommandBranch {
	
	private IRobot myRobot;
	
	public AskWithCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myRobot = aRobot;
	}

	@Override
	protected int evalConditionInNode(IReadableInput... aList)
			throws InvalidInputNumberException, InvalidNodeUsageException {
		//TODO
		return 0;
	}

}
