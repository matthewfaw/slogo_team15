package back_end.model.command;

import back_end.model.node.IReadableInput;

public class QuotientCommand implements ICommand {
	
	public QuotientCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		return aList[0].getValue() / aList[1].getValue();
	}

}