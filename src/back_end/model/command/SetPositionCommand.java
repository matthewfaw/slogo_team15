package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


public class SetPositionCommand implements ICommand {

    private IRobot myRobot;

    public SetPositionCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myRobot = aRobot;
    }

	@Override
    public double eval (IReadableInput ... aList) {
        double posX = Math.abs(myRobot.getCoordinate().getX() - aList[0].getValue());
        double posY = Math.abs(myRobot.getCoordinate().getY() - aList[1].getValue());
        double returnVal = Math.sqrt(Math.pow(posX, 2) + Math.pow(posY, 2));
        myRobot.setX(aList[0].getValue());
        myRobot.setY(aList[1].getValue());
        return returnVal;
    }

}
