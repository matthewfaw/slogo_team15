package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


import back_end.model.exception.InvalidNodeUsageException;
public class ForCommand extends ICommandBranch {

    private boolean myFirst;
    private IModifiableEnvironmentState myEnvironment;

    public ForCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myEnvironment = aEnvironment;
        myFirst = true;
    }

    @Override
    public int evalCondition (IReadableInput ... aList) throws InvalidNodeUsageException {
        if (myFirst) {
            myFirst = false;
            myEnvironment.assignVariable(aList[0].getName(), aList[1].getValue());
        }
        if (myEnvironment.getVariableValue(aList[0].getName()) < aList[2].getValue()) {
            myEnvironment.assignVariable(aList[0].getName(),
                                   (myEnvironment.getVariableValue(aList[0].getName()) + aList[3].getValue()));
            return 0;
        }
        return -1;
    }

}
