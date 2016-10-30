package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;


public class RightCommand extends RotationCommand {

    private Robot myRobot;

    public RightCommand (Robot aRobot) {
        super(aRobot);
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        double rotation = getRotation(aList);
        myRobot.setRotation(myRobot.getRotation() + rotation);
        return aList[0].getValue();
    }

}
