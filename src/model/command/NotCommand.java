package model.command;

import model.node.IReadableInput;

public class NotCommand implements ICommand {
	
	public NotCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		double returnVal = (aList[0].getValue() == 0) ? 1 : 0;
		return returnVal;
	}

}