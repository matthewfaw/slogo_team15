package model.command;

import java.util.List;

import model.node.IReadableInput;

public interface ICommand {
	

	public double eval(IReadableInput... aList);
	
}
