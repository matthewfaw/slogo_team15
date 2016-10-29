package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;


public class MinusCommand implements ICommand {

    public MinusCommand(Robot aRobot, Environment aEnvironment, String aCommandName) {
    }

    @Override
    public double eval (IReadableInput ... aList) {
        return 0 - aList[0].getValue();
    }

}
