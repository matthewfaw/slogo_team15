package back_end.model.command;

import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class AskCommand extends ICommandBranch {
	
	private IRobot myRobot;
	
	public AskCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myRobot = aRobot;
	}
	
	@Override
	public double eval(IReadableInput...aList) throws InvalidNodeUsageException {
		//myRobot.endTemporaryActiveTurtles();
		return super.eval(aList);
	}

	@Override
	public int evalConditionInNode (IReadableInput...aList) throws InvalidInputNumberException, InvalidNodeUsageException {
		int[] array = new int[aList.length];
		for (int i = 0; i < aList.length; i++) {
			array[i] = (int) aList[i].getValue();
		}
		//myRobot.setActiveTurtles(array, false);
		return 1;
	}

}
