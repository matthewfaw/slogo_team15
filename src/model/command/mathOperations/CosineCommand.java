package model.command.mathOperations;

import model.command.ICommand;
import model.exception.ArgumentException;

public class CosineCommand implements ICommand {
	
	public CosineCommand() {
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		if (aList.length != 1) {
			throw new ArgumentException("Method Evaluation needs one argument");
		}
		return Math.cos(Double.parseDouble(aList[0]));
	}

}