package back_end.model.command;

import java.awt.Point;
import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


public class BackwardCommand extends MovementCommand {

    private IRobot myRobot;

    public BackwardCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        super(aRobot, aEnvironment, aCommandName);
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        Point p = getXYCoordinate(aList);
        myRobot.setX(myRobot.getCoordinate().getX() - p.getX());
        myRobot.setY(myRobot.getCoordinate().getY() - p.getY());
        return aList[0].getValue();
    }

}
