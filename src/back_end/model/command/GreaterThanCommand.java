package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;
import back_end.model.states.IModifiableVariableState;


import back_end.model.exception.InvalidNodeUsageException;
public class GreaterThanCommand implements ICommand {

    public GreaterThanCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        double returnVal = (aList[0].getValue() > aList[1].getValue()) ? 1 : 0;
        return returnVal;
    }

}
