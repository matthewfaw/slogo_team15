package model.command;

import model.node.IReadableInput;

public class RemainderCommand implements ICommand {
	
	public RemainderCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		return aList[0].getValue() % aList[1].getValue();
	}

}