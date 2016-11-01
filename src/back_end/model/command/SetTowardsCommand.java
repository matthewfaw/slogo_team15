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
        double adj = Math.abs(aList[0].getValue() - myRobot.getCoordinate().getX());
        double opp = Math.abs(aList[1].getValue() - myRobot.getCoordinate().getY());
        double turn = Math.abs(Math.atan(opp / adj)) * (180 / Math.PI);
        double returnVal = myRobot.getRotation() - turn;
        myRobot.setRotation(turn);
        return returnVal;
    }
}
