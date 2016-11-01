package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;

public class CustomCommand extends ICommandBranch {
	
	private IModifiableEnvironmentState myEnvironment;
	private String myName;
	
	public CustomCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myEnvironment = aEnvironment;
		myName = aCommandName;
	}
	
	public IReadableInput getFunction() {
		return myEnvironment.getMethodExecutionNode(myName);
	}

	@Override
	protected int evalConditionInNode(IReadableInput... aList)
			throws InvalidInputNumberException, InvalidNodeUsageException {
		int counter = 0;
		for (String variable: myEnvironment.getVariableKeySet()) {
			myEnvironment.assignVariable(variable, aList[counter].getValue());
			++counter;
		}
		errorCheckForTooManyInputs(aList.length, counter + 1);
		return 1;
	}
	

}
