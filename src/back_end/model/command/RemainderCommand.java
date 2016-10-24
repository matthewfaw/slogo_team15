package back_end.model.command;

import back_end.model.node.IReadableInput;

public class RemainderCommand implements ICommand {
	
	public RemainderCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		return aList[0].getValue() % aList[1].getValue();
	}

}