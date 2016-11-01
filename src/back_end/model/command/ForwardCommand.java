package back_end.model.command;

import java.awt.Point;
import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

import back_end.model.exception.InvalidNodeUsageException;
public class ForwardCommand extends MovementCommand {

    private IRobot myRobot;

    public ForwardCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        super(aRobot, aEnvironment, aCommandName);
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        Point p = getXYCoordinate(aList);
        myRobot.setCoordinates(myRobot.getCurrentCoordinate().getX() + p.getX(), myRobot.getCurrentCoordinate().getY() + p.getY());
        return aList[0].getValue();
    }

}
