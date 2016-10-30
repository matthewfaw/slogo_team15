package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;


public class SetTowardsCommand implements ICommand {
    private Robot myRobot;

    public SetTowardsCommand (Robot aRobot) {
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        double adj = aList[0].getValue() - myRobot.getX();
        double opp = aList[1].getValue() - myRobot.getY();
        double returnVal = 180 - Math.abs(myRobot.getRotation() - Math.atan(opp / adj));
        if (aList[0].getValue() > myRobot.getX()) {
            myRobot.setRotation(myRobot.getRotation() - returnVal);
        }
        else {
            myRobot.setRotation(myRobot.getRotation() + returnVal);
        }
        return returnVal;
    }
}
