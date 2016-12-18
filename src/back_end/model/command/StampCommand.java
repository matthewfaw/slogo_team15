package back_end.model.command;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class StampCommand implements ICommand {

    private IRobot myRobot;
    private IModifiableEnvironmentState myEnvironment;

    public StampCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myRobot = aRobot;
        myEnvironment = aEnvironment;
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
    	return myEnvironment.stamp(myRobot);
    }

}
