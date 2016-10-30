package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


public class IfCommand extends ICommandBranch {

    private boolean myFirstExecution;

    public IfCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myFirstExecution = true;
    }

	@Override
    public int evalCondition (IReadableInput ... aList) {
        if (myFirstExecution) {
            myFirstExecution = false;
            return (aList[0].getValue() != 0) ? 0 : -1;
        }
        return -1;
    }

}
