package model.command;

import model.node.IReadableInput;

public class CosineCommand implements ICommand {
	
	public CosineCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		return Math.cos(aList[0].getValue());
	}

}