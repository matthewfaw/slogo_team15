package back_end.model.command;

import java.util.Arrays;

import back_end.model.node.IReadableInput;
import back_end.model.states.Scope;


public class MakeUserInstructionCommand implements ICommand {
	
	private Scope myScope;
	
	public MakeUserInstructionCommand(Scope aScope, String aName) {
		myScope = aScope;
//		myScope.assignMethod(aName, null, null);
	}

	@Override
	public double eval(IReadableInput... aList) {
		IReadableInput[] variableList = Arrays.copyOfRange(aList, 2, aList.length);
		for (IReadableInput variable : variableList) {
//			if (!myScope.containsVariable(variable.getName())) {
//				return 0;
//			}
		}
		myScope.assignMethod(aList[0].getName(), aList[1], variableList);
		return 1;
	}

}
