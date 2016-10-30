package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.IModifiableVariableState;


import back_end.model.exception.InvalidNodeUsageException;
public class TangentCommand implements ICommand {

    public TangentCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        return Math.tan(aList[0].getValue());
    }

}
