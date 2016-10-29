package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;


public class SetHeadingCommand extends RotationCommand {

    private Robot myRobot;

    public SetHeadingCommand(Robot aRobot, Environment aEnvironment, String aCommandName) {
        super(aRobot, aEnvironment, aCommandName);
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        double rotation = getRotation(aList);
        double returnVal = Math.abs(myRobot.getRotation() - rotation);
        myRobot.setRotation(rotation);
        return returnVal;
    }

}
