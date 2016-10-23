package model.command;

import model.node.IReadableInput;
import model.states.Scope;

public class ForCommand implements ICommand {
	
	private boolean myFirst;
	private Scope myScope;
	
	public ForCommand(Scope aScope) {
		myScope = aScope;
		myFirst = true;
	}
	
	public double evalCondition(IReadableInput...aList) {
		if (myFirst)  {
			myFirst = false;
			myScope.assignVariable(aList[0].getName(), aList[1].getValue());
		}
		if (myScope.getValue(aList[0].getName()) < aList[2].getValue()) {
			myScope.assignVariable(aList[0].getName(), (myScope.getValue(aList[0].getName()) + aList[3].getValue()));
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public double eval(IReadableInput...aList) {
		return aList[aList.length - 1].getValue();
	}

}
