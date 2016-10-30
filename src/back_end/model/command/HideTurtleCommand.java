package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;
import back_end.model.states.IModifiableVariableState;


public class HideTurtleCommand implements ICommand {

    private Robot myRobot;

    public HideTurtleCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
        super();
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        myRobot.setVisible(false);
        return 0;
    }

}
