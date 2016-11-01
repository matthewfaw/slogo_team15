package back_end.model.command;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public abstract class RotationCommand implements ICommand {

    public RotationCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
    }

    public double getRotation (IReadableInput ... aList) throws InvalidNodeUsageException {
        double rotationValue = aList[0].getValue() % 360;
//        if (rotationValue < 0) {
//            rotationValue = 360 + rotationValue;
//        }
        return rotationValue;

    }

}
