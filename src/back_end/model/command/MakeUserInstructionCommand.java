package back_end.model.command;

import java.util.Arrays;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;


public class MakeUserInstructionCommand implements ICommand {
	
	private Environment myEnvironment;
	
	public MakeUserInstructionCommand(Robot aRobot, Environment aEnvironment, String aCommandName) {
		myEnvironment = aEnvironment;
		myEnvironment.assignMethod(aCommandName, null, null);
	}

	@Override
	public double eval(IReadableInput... aList) {
		IReadableInput[] variableList = Arrays.copyOfRange(aList, 2, aList.length);
		myEnvironment.assignMethod(aList[0].getName(), aList[1], variableList);
		return 1;
	}

}
