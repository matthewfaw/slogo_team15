package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.IModifiableVariableState;


public class NotEqualCommand implements ICommand {

    public NotEqualCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
    }

    @Override
    public double eval (IReadableInput ... aList) {
        double returnVal = (aList[0].getValue() != aList[1].getValue()) ? 1 : 0;
        return returnVal;
    }

}
