package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.IModifiableVariableState;


import back_end.model.exception.InvalidNodeUsageException;
public class PowerCommand implements ICommand {

    public PowerCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        return Math.pow(aList[0].getValue(), aList[1].getValue());
    }

}
