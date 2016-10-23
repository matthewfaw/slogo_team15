package model.command;

import model.node.IReadableInput;

public class PowerCommand implements ICommand {
	
	public PowerCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		return Math.pow(aList[0].getValue(), aList[1].getValue());
	}

}