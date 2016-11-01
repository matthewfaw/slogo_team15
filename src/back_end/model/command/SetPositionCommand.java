package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


import back_end.model.exception.InvalidNodeUsageException;
public class SetPositionCommand implements ICommand, ICommandTurtle {

    private IRobot myRobot;

    public SetPositionCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myRobot = aRobot;
    }

	@Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        double posX = Math.abs(myRobot.getCurrentCoordinate().getX() - aList[0].getValue());
        double posY = Math.abs(myRobot.getCurrentCoordinate().getY() - aList[1].getValue());
        double returnVal = Math.sqrt(Math.pow(posX, 2) + Math.pow(posY, 2));
        myRobot.setCoordinates(aList[0].getValue(), aList[1].getValue());
        return returnVal;
    }

}
