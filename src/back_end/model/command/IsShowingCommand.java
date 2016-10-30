package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


public class IsShowingCommand implements ICommand {

    private IRobot myRobot;

    public IsShowingCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        double returnVal = (myRobot.isVisible()) ? 1 : 0;
        return returnVal;
    }

}
