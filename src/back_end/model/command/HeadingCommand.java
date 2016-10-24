package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;


public class HeadingCommand implements ICommand {

    private Robot myRobot;

    public HeadingCommand (Robot aRobot) {
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        return myRobot.getRotation();
    }

}
