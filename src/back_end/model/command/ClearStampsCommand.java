package back_end.model.command;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class ClearStampsCommand implements ICommand {

    private IModifiableEnvironmentState myEnvironment;

    public ClearStampsCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myEnvironment = aEnvironment;
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
    	return myEnvironment.clearAllStamps();
    }

}
