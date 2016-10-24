package back_end.model.command;

import java.util.List;

import back_end.model.node.IReadableInput;

public interface ICommand {
	

	public double eval(IReadableInput... aList);
	
}
