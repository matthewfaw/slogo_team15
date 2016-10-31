package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.Environment;
import back_end.model.states.IModifiableEnvironmentState;


import back_end.model.exception.InvalidNodeUsageException;
public class ClearScreenCommand extends HomeCommand {

    public ClearScreenCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        super(aRobot, aEnvironment, aCommandName);
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        return super.eval(aList);
    }

}
