package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

import back_end.model.exception.InvalidNodeUsageException;

public class SetTowardsCommand implements ICommand {
    private IRobot myRobot;

    public SetTowardsCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        double adj = aList[0].getValue() - myRobot.getCoordinate().getX();
        double opp = aList[1].getValue() - myRobot.getCoordinate().getY();
        double returnVal = 180 - Math.abs(myRobot.getRotation() - Math.atan(opp / adj));
        if (aList[0].getValue() > myRobot.getCoordinate().getX()) {
            myRobot.setRotation(myRobot.getRotation() - returnVal);
        }
        else {
            myRobot.setRotation(myRobot.getRotation() + returnVal);
        }
        return returnVal;
    }
}
