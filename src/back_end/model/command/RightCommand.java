package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

import back_end.model.exception.InvalidNodeUsageException;

public class RightCommand extends RotationCommand {

    private IRobot myRobot;

    public RightCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        super(aRobot, aEnvironment, aCommandName);
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        double rotation = getRotation(aList);
        myRobot.setRotation(myRobot.getRotation() - rotation);
        return aList[0].getValue();
    }

}
