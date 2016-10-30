package back_end.model.command;

import java.util.Arrays;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;
import back_end.model.states.IModifiableVariableState;


public class MakeUserInstructionCommand implements ICommand {
	
	private IModifiableVariableState myEnvironment;
	
	public MakeUserInstructionCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
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
