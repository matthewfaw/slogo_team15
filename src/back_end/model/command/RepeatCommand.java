package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.states.Environment;


public class RepeatCommand extends ICommandBranch {

    private int myNumberTimesRun;

    public RepeatCommand (Environment aEnvironment) {
        myNumberTimesRun = 1;
    }

    @Override
    public int evalCondition (IReadableInput ... aList) {
        if (myNumberTimesRun < aList[0].getValue()) {
            myNumberTimesRun++;
            return 0;
        }
        else {
            return -1;
        }
    }

}
