package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


import back_end.model.exception.InvalidNodeUsageException;
public class RepeatCommand extends ICommandBranch {

    private int myNumberTimesRun;

    public RepeatCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myNumberTimesRun = 1;
    }

    @Override
    public int evalCondition (IReadableInput ... aList) throws InvalidNodeUsageException {
        if (myNumberTimesRun < aList[0].getValue()) {
            myNumberTimesRun++;
            return 0;
        }
        else {
            return -1;
        }
    }

}
