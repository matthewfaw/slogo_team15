package model.command;

import model.node.IReadableInput;

public class AndCommand implements ICommand {
	
	public AndCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		double returnVal = (aList[0].getValue() != 0 && aList[1].getValue() != 0) ? 1 : 0;
		return returnVal;
	}

}