package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;


public class IsPenDownCommand implements ICommand {

    private Robot myRobot;

    public IsPenDownCommand(Robot aRobot, Environment aEnvironment, String aCommandName) {
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        double returnVal = (myRobot.isPenDown()) ? 1 : 0;
        return returnVal;
    }

}
