package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.Environment;
import back_end.model.states.IModifiableEnvironmentState;


public class LeftCommand extends RotationCommand {

    private IRobot myRobot;

    public LeftCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
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
