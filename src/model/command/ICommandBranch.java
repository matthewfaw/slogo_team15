package model.command;

import model.node.IReadableInput;

public interface ICommandBranch extends ICommand {

	public double eval(IReadableInput... aList);
	
	public int evalCondition(IReadableInput...aList);
}
