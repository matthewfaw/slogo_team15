package model.command;

import model.node.IReadableInput;

public class PiCommand implements ICommand {
	
	public PiCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		return Math.PI;
	}

}