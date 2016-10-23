package model.command;

import model.node.IReadableInput;

public class NaturalLogCommand implements ICommand {
	
	public NaturalLogCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		return Math.log(aList[0].getValue());
	}

}