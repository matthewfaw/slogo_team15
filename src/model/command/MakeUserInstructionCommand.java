package model.command;

import model.node.IReadableInput;
import model.states.MethodState;

public class MakeUserInstructionCommand implements ICommand {
	
	private MethodState myMethod;
	
	public MakeUserInstructionCommand(MethodState aMethod) {
		myMethod = aMethod;
	}

	@Override
	public double eval(IReadableInput... aList) {
		return 1;
	}

}
