package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;

public class TellCommand implements ICommand {
	
	private IRobot myRobot;
	
	public TellCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		int[] array = new int[aList.length];
		for (int i = 0; i < aList.length; i++) {
			array[i] = (int) aList[i].getValue();
		}
		myRobot.setActiveTurtles(array);
		return aList[0].getValue();
	}

}
