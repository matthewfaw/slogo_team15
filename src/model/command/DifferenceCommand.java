package model.command;

import model.node.IReadableInput;

public class DifferenceCommand implements ICommand {
	
	public DifferenceCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		return aList[0].getValue() - aList[1].getValue();
	}

}