package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
public class DoTimesCommand extends ICommandBranch {

    private int myNumberTimesRun;
    private IModifiableEnvironmentState myEnvironment;

    public DoTimesCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myNumberTimesRun = 0;
        myEnvironment = aEnvironment;
    }

	@Override
	protected int evalConditionInNode(IReadableInput... aList)
			throws InvalidInputNumberException, InvalidNodeUsageException {
		errorCheckForTooManyInputs(aList.length, 2);
		myEnvironment.assignVariable(aList[0].getName(), myNumberTimesRun);
	    if (myNumberTimesRun < aList[1].getValue()) {
	        myNumberTimesRun++;
	        return 0;
	    } else {
	        return -1;
	    }
	}

}
