package model.command.booleanOperations;

import model.command.ICommand;
import model.exception.ArgumentException;

public class NotCommand implements ICommand {
	
	public NotCommand() {
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		if (aList.length != 1) {
			throw new ArgumentException("Method Evaluation needs one argument");
		}
		double returnVal = (Double.parseDouble(aList[0]) == 0) ? 1 : 0;
		return returnVal;
	}

}