package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.Environment;
import back_end.model.states.IModifiableEnvironmentState;


public class MakeVariableCommand implements ICommand {

    private IModifiableEnvironmentState myScope;

    public MakeVariableCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        myScope = aEnvironment;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        System.out.println(aList[1].getValue());
        myScope.assignVariable(aList[0].getName(), aList[1].getValue());
        return aList[1].getValue();
    }
}
