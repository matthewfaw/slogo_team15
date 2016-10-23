package model.command;

import model.node.IReadableInput;

public class ArcTangentCommand implements ICommand {
	
	public ArcTangentCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		return Math.atan(aList[0].getValue());
	}

}