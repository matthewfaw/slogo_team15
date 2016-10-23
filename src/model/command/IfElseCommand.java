package model.command;

import model.node.IReadableInput;
import model.states.Scope;

public class IfElseCommand extends IfCommand {
	
	public IfElseCommand(Scope aScope) {
		super(aScope);
	}
	
	public double evalCondition(IReadableInput...aList) {
		double returnVal = (super.evalCondition(aList) == 0) ? 0 : 1;
		return returnVal; 
	}

	@Override
	public double eval(IReadableInput...aList) {
		return super.eval(aList);
	}

}