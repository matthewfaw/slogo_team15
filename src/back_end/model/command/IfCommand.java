package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.states.Environment;


public class IfCommand extends ICommandBranch {

    private boolean myFirstExecution;

    public IfCommand (Environment aEnvironment) {
        myFirstExecution = true;
    }

    @Override
    public int evalCondition (IReadableInput ... aList) {
        if (myFirstExecution) {
            myFirstExecution = false;
            return (aList[0].getValue() != 0) ? 0 : -1;
        }
        return -1;
    }

}
