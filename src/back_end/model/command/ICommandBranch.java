package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.command.ICommand;

public interface ICommandBranch extends ICommand {

	public double eval(IReadableInput... aList);
	
	public int evalCondition(IReadableInput...aList);
}
