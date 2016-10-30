package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.IModifiableVariableState;


import back_end.model.exception.InvalidNodeUsageException;
public class DoTimesCommand extends ICommandBranch {

    private int myNumberTimesRun;
    private IModifiableVariableState myEnvironment;

    public DoTimesCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
        myNumberTimesRun = 0;
        myEnvironment = aEnvironment;
    }

    @Override
    public int evalCondition (IReadableInput ... aList) throws InvalidNodeUsageException {
        myEnvironment.assignVariable(aList[0].getName(), myNumberTimesRun);
        if (myNumberTimesRun < aList[1].getValue()) {
            myNumberTimesRun++;
            return 0;
        }
        else {
            return -1;
        }
    }

}
