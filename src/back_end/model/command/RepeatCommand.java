package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.states.Scope;


public class RepeatCommand implements ICommandBranch {

    private int myNumberTimesRun;

    public RepeatCommand (Scope aScope) {
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

    @Override
    public double eval (IReadableInput ... aList) {
        return aList[0].getValue();
    }

}
