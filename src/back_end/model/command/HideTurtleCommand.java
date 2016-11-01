package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

import back_end.model.exception.InvalidNodeUsageException;

public class HideTurtleCommand implements ICommand {

    private IRobot myRobot;

    public HideTurtleCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        super();
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        myRobot.setVisible(false);
        return 0;
    }

}
