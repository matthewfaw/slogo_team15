package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.IModifiableVariableState;


public class PenDownCommand implements ICommand {

    private Robot myRobot;

    public PenDownCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
        super();
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        myRobot.setPenDown(true);
        return 1;
    }

}
