package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.states.Scope;

public class IfCommand implements ICommandBranch {
	
	private boolean myFirstExecution;
	
	public IfCommand(Scope aScope) {
		myFirstExecution = true;
	}
	
	@Override
	public int evalCondition(IReadableInput...aList) {
		if (myFirstExecution) {
			myFirstExecution = false;
			System.out.println("derp");
			return (aList[0].getValue() != 0) ? 0 : -1;
		} else {
			return -1;
		}
	}

	@Override
	public double eval(IReadableInput...aList) {
		return aList[aList.length - 1].getValue();
	}

}
