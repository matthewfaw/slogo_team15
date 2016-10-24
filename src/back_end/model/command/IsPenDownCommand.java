package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;


public class IsPenDownCommand implements ICommand {

    private Robot myRobot;

    public IsPenDownCommand (Robot aRobot) {
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        double returnVal = (myRobot.isPenDown()) ? 1 : 0;
        return returnVal;
    }

}
