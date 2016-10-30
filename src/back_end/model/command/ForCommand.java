package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.IModifiableVariableState;


public class ForCommand extends ICommandBranch {

    private boolean myFirst;
    private IModifiableVariableState myEnvironment;

    public ForCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
        myEnvironment = aEnvironment;
        myFirst = true;
    }

    @Override
    public int evalCondition (IReadableInput ... aList) {
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
