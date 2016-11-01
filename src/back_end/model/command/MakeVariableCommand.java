package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

import back_end.model.exception.InvalidNodeUsageException;

public class MakeVariableCommand implements ICommand {

    private IModifiableEnvironmentState myScope;

    public MakeVariableCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myScope = aEnvironment;
    }

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        System.out.println(aList[1].getValue());
        myScope.assignVariable(aList[0].getName(), aList[1].getValue());
        return aList[1].getValue();
    }
}
