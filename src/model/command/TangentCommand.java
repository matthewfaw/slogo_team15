package model.command;

import model.exception.ArgumentException;

public class TangentCommand implements ICommand {
	
	public TangentCommand() {
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		if (aList.length != 1) {
			throw new ArgumentException("Method Evaluation needs one argument");
		}
		return Math.tan(Double.parseDouble(aList[0]));
	}

}