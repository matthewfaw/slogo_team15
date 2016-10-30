package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.IModifiableVariableState;


import back_end.model.exception.InvalidNodeUsageException;
public class IsPenDownCommand implements ICommand {

    private Robot myRobot;

    public IsPenDownCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        double returnVal = (myRobot.isPenDown()) ? 1 : 0;
        return returnVal;
    }

}
