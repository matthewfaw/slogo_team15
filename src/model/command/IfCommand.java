package model.command;

import model.node.IReadableInput;
import model.states.Scope;

public class IfCommand implements ICommandBranch {
	
	public IfCommand(Scope aScope) {
	}
	
	@Override
	public int evalCondition(IReadableInput...aList) {
		int returnVal = (aList[0].getValue() != 0) ? 0 : -1;
		return returnVal;
	}

	@Override
	public double eval(IReadableInput...aList) {
		return aList[aList.length - 1].getValue();
	}

}
