package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


import back_end.model.exception.InvalidNodeUsageException;
public class IsPenDownCommand implements ICommand {

    private IRobot myRobot;

    public IsPenDownCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        double returnVal = (myRobot.getPenInformation().isPenUp()) ? 0 : 1;
        return returnVal;
    }

}
