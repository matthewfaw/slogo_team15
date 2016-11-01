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
    	double xDisplacement = aList[0].getValue() - myRobot.getCurrentCoordinate().getX();
    	double yDisplacement = aList[1].getValue() - myRobot.getCurrentCoordinate().getY();
    	double newAngle = Math.toDegrees(Math.atan(yDisplacement/xDisplacement));
    	if (xDisplacement < 0) {
    		newAngle += Math.toDegrees(Math.PI);
    	}
    	
    	double angleDisplacement = newAngle - (myRobot.getRotation() + Math.PI/2);
    	myRobot.setRotation(newAngle - Math.toDegrees(Math.PI/2));

        return angleDisplacement;
    }
}
