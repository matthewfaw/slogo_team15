package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.states.Scope;

public class CustomCommand implements ICommandBranch {
	
	private Scope myScope;
	private String myName;
	
	public CustomCommand(Scope aScope, String aName) {
		myScope = aScope;
		myName = aName;
	}

	@Override
	public double eval(IReadableInput... aList) {
		return aList[aList.length - 1].getValue();
	}

	@Override
	public int evalCondition(IReadableInput... aList) {
		int counter = 0;
		for (String variable: myScope.getVariablesInMethod(myName)) {
			myScope.assignVariable(variable, aList[counter].getValue());
			++counter;
		}
//		IReadableInput[] variableList = myScope.getVariablesInMethod(myName);
//		for (int i = 0; i < variableList.length; i++) {
//			myScope.assignVariable(variableList[i].getName(), aList[i].getValue());
//		}
		return 1;
	}
	
	public void setScope() {
		myScope.swapScope(myName);
	}
	
	public void resetScope() {
		myScope.swapScope("DEFAULT");
	}
	
	public IReadableInput getFunction() {
		return myScope.getMethodToEvaluate(myName);
	}
	

}
