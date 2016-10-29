package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;


public class YCoordinateCommand implements ICommand {

    private Robot myRobot;

    public YCoordinateCommand (Robot aRobot) {
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        return myRobot.getCoordinates().getY();
    }

}
