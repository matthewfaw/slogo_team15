package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.states.MethodState;

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
