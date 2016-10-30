package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;
import back_end.model.states.IModifiableVariableState;


public class LeftCommand extends RotationCommand {

    private Robot myRobot;

    public LeftCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
        super(aRobot, aEnvironment, aCommandName);
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        double rotation = getRotation(aList);
        myRobot.setRotation(myRobot.getRotation() - rotation);
        return aList[0].getValue();
    }

}
