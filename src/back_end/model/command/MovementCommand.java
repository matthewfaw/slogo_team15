package back_end.model.command;

import java.awt.Point;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


public abstract class MovementCommand implements ICommand {

    private IRobot myRobot;

    public MovementCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myRobot = aRobot;
    }

	public Point getXYCoordinate (IReadableInput ... aList) throws InvalidNodeUsageException {
        double XPos = Math.cos(Math.toRadians(myRobot.getCurrentRotation()) + Math.PI/2) * aList[0].getValue();
        double YPos = Math.sin(Math.toRadians(myRobot.getCurrentRotation()) + Math.PI/2) * aList[0].getValue();
//        if (myRobot.getRotation() > 90 && myRobot.getRotation() < 360) {
//            XPos = 0 - XPos;
//            YPos = 0 - YPos;
//        }
        Point p = new Point();
        p.setLocation(XPos, YPos);
        return p;
    }

}
