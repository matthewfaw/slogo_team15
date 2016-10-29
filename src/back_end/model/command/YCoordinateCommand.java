package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;


public class YCoordinateCommand implements ICommand {

    private Robot myRobot;

    public YCoordinateCommand(Robot aRobot, Environment aEnvironment, String aCommandName) {
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        return myRobot.getCoordinates().getY();
    }

}
