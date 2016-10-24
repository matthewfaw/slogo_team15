package back_end.model.command;

import back_end.model.node.IReadableInput;

public class NotCommand implements ICommand {
	
	public NotCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		double returnVal = (aList[0].getValue() == 0) ? 1 : 0;
		return returnVal;
	}

}