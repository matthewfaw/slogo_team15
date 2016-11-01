package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.node.leaf_nodes.ConstantNode;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;
import back_end.model.exception.InvalidNodeUsageException;
public class HomeCommand extends SetPositionCommand implements ICommandTurtle {

    public HomeCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        super(aRobot, aEnvironment, aCommandName);
    }

	@Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        IReadableInput[] zeroList =
                { (IReadableInput) new ConstantNode(null, 0, "0", null), (IReadableInput) new ConstantNode(null, 0, "0", null) };
        return super.eval(zeroList);
    }

}
