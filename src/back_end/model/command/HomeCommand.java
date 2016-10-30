package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.node.value_nodes.ConstantNode;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;


public class HomeCommand extends SetPositionCommand {

    public HomeCommand(Robot aRobot, Environment aEnvironment, String aCommandName) {
        super(aRobot, aEnvironment, aCommandName);
    }

    @Override
    public double eval (IReadableInput ... aList) {
        IReadableInput[] zeroList =
                { (IReadableInput) new ConstantNode(null, 0, "0", null), (IReadableInput) new ConstantNode(null, 0, "0", null) };
        return super.eval(zeroList);
    }

}
