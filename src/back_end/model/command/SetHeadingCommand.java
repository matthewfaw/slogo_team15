package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


import back_end.model.exception.InvalidNodeUsageException;
public class SetHeadingCommand extends RotationCommand {

    private IRobot myRobot;

    public SetHeadingCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        super(aRobot, aEnvironment, aCommandName);
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        double rotation = getRotation(aList);
        double returnVal = Math.abs(myRobot.getRotation() - rotation);
        myRobot.setRotation(rotation);
        return returnVal;
    }

}
