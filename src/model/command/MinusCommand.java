package model.command;

import model.node.IReadableInput;

public class MinusCommand implements ICommand {
	
	public MinusCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		return 0 - aList[0].getValue();
	}

}