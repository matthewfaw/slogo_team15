package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.states.Scope;

public class DoTimesCommand implements ICommandBranch {
	
	private int myNumberTimesRun;
	private Scope myScope;
	
	public DoTimesCommand(Scope aScope) {
		myNumberTimesRun = 0;
		myScope = aScope;
	}
	
	@Override
	public int evalCondition(IReadableInput...aList) {
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
