package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;


public class ClearScreenCommand extends HomeCommand {

    public ClearScreenCommand(Robot aRobot, Environment aEnvironment, String aCommandName) {
        super(aRobot, aEnvironment, aCommandName);
    }

    @Override
    public double eval (IReadableInput ... aList) {
        return super.eval(aList);
    }

}
