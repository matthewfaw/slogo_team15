package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.states.Environment;


public class DoTimesCommand extends ICommandBranch {

    private int myNumberTimesRun;
    private Environment myEnvironment;

    public DoTimesCommand (Environment aEnvironment) {
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
