package model.command;

import model.node.IReadableInput;

public interface ICommand {
	

	public double eval(IReadableInput...aList);
	
}
