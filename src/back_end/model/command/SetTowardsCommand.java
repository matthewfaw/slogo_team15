package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;
import back_end.model.states.IModifiableVariableState;


public class SetTowardsCommand implements ICommand {
    private Robot myRobot;

    public SetTowardsCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        double adj = aList[0].getValue() - myRobot.getCoordinates().getX();
        double opp = aList[1].getValue() - myRobot.getCoordinates().getY();
        double returnVal = 180 - Math.abs(myRobot.getRotation() - Math.atan(opp / adj));
        if (aList[0].getValue() > myRobot.getCoordinates().getX()) {
            myRobot.setRotation(myRobot.getRotation() - returnVal);
        }
        else {
            myRobot.setRotation(myRobot.getRotation() + returnVal);
        }
        return returnVal;
    }
}
