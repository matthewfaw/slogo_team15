package back_end.model.command;

import back_end.model.node.IReadableInput;

public class SumCommand implements ICommand {
	
	public SumCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		return aList[0].getValue() + aList[1].getValue();
	}

}
