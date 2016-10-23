package model.command;

import model.node.IReadableInput;

public class EqualCommand implements ICommand {
	
	public EqualCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		double returnVal = (aList[0].getValue() == aList[1].getValue()) ? 1 : 0;
		return returnVal;
	}

}