package model.command;

import model.node.IReadableInput;
import model.states.Scope;

public class DoTimesCommand implements ICommand {
	
	private int myNumberTimesRun;
	private Scope myScope;
	
	public DoTimesCommand(Scope aScope) {
		myNumberTimesRun = 0;
		myScope = aScope;
	}
	
	public double evalCondition(IReadableInput...aList) {
		myScope.assignVariable(aList[0].getName(), aList[1].getValue());
		if (myNumberTimesRun < myScope.getValue(aList[0].getName())) {
			myNumberTimesRun++;
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
