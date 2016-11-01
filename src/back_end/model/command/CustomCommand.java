package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.node.inner_nodes.list_nodes.ListNode;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;

public class CustomCommand extends ICommandBranch {
	
	private IModifiableEnvironmentState myEnvironment;
	private String myName;
	private boolean myFirstExecution;
	
	public CustomCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myEnvironment = aEnvironment;
		myName = aCommandName;
		myFirstExecution = true;
	}
	
	public IReadableInput getFunction() {
		return myEnvironment.getMethodExecutionNode(myName);
	}

	@Override
	protected int evalConditionInNode(IReadableInput... aList)
			throws InvalidInputNumberException, InvalidNodeUsageException {
		if (myFirstExecution) {
			int counter = 0;
			for (IReadableInput variable: myEnvironment.getMethodVariables(myName)) {
				myEnvironment.assignVariable(variable.getName(), aList[counter].getValue());
				++counter;
			}
			errorCheckForTooManyInputs(aList.length, counter + 1);
			myFirstExecution = false;
			return 0;
		}
		return -1;
	}
	
}
