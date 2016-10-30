package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.Environment;
import back_end.model.states.IModifiableEnvironmentState;


public class ShowTurtleCommand implements ICommand {

    private IRobot myRobot;

    public ShowTurtleCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        super();
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        myRobot.setVisible(true);
        return 1;
    }

}
