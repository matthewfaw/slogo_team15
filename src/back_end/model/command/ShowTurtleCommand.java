package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;


public class ShowTurtleCommand implements ICommand {

    private Robot myRobot;

    public ShowTurtleCommand(Robot aRobot, Environment aEnvironment, String aCommandName) {
        super();
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        myRobot.setVisible(true);
        return 1;
    }

}
