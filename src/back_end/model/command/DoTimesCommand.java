package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class DoTimesCommand extends ICommandBranch {

    private int myNumberTimesRun;
    private IModifiableEnvironmentState myEnvironment;

    public DoTimesCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myNumberTimesRun = 0;
        myEnvironment = aEnvironment;
    }

    @Override
    public int evalCondition (IReadableInput ... aList) {
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
