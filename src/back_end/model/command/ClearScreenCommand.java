package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;
import back_end.model.states.IModifiableVariableState;


import back_end.model.exception.InvalidNodeUsageException;
public class ClearScreenCommand extends HomeCommand {

    public ClearScreenCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
        super(aRobot, aEnvironment, aCommandName);
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        return super.eval(aList);
    }

}
