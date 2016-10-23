package model.command;

import model.node.IReadableInput;
import model.states.Scope;

public class RepeatCommand implements ICommand {
	
	private int myNumberTimesRun;
	
	public RepeatCommand(Scope aScope) {
		myNumberTimesRun = 0;
	}
	
	public double evalCondition(IReadableInput...aList) {
		if (myNumberTimesRun < aList[0].getValue()) {
			myNumberTimesRun++;
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public double eval(IReadableInput...aList) {
		return aList[0].getValue();
	}

}
