package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.states.Scope;

public class IfCommand implements ICommand {
	
	public IfCommand(Scope aScope) {
	}
	
	public double evalCondition(IReadableInput...aList) {
		double returnVal = (aList[0].getValue() != 0) ? 0 : -1;
		return returnVal;
	}

	@Override
	public double eval(IReadableInput...aList) {
		return aList[aList.length - 1].getValue();
	}

}
