package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


import back_end.model.exception.InvalidNodeUsageException;
public class NaturalLogCommand implements ICommand {

    public NaturalLogCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        return Math.log(aList[0].getValue());
    }

}
