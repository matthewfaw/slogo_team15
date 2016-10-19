package model.command.mathOperations;

import model.command.ICommand;
import model.exception.ArgumentException;

public class MinusCommand implements ICommand {
	
	public MinusCommand() {
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		if (aList.length != 1) {
			throw new ArgumentException("Method Evaluation needs one argument");
		}
		return 0 - Double.parseDouble(aList[0]);
	}

}