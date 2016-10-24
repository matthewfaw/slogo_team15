package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.states.Scope;

public class IfElseCommand extends IfCommand {
	
	private boolean myExecuteMethod; 
	
	public IfElseCommand(Scope aScope) {
		super(aScope);
		myExecuteMethod = true;
	}
	
	public int evalCondition(IReadableInput...aList) {
		if (myExecuteMethod) {
			int returnVal = (super.evalCondition(aList) == 0) ? 0 : 1;
			myExecuteMethod = false;
			return returnVal; 
		} else {
			return -1;
		}
	}

	@Override
	public double eval(IReadableInput...aList) {
		return super.eval(aList);
	}

}
