package back_end.model.command;


import back_end.model.node.IReadableInput;
import back_end.model.states.Scope;

public class MakeVariableCommand implements ICommand {
	
	private Scope myScope;
	
	public MakeVariableCommand(Scope aScope) {
		myScope = aScope;
	}

	@Override
	public double eval(IReadableInput...aList) {
		System.out.println(aList[1].getValue());
		myScope.assignVariable(aList[0].getName(), aList[1].getValue());
		return aList[1].getValue();
	}

}
