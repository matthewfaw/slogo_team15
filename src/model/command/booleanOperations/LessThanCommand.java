package model.command.booleanOperations;

import model.command.ICommand;
import model.exception.ArgumentException;

public class LessThanCommand implements ICommand {
	
	public LessThanCommand() {
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		if (aList.length != 2) {
			throw new ArgumentException("Method Evaluation needs two arguments");
		}
		double returnVal = (Double.parseDouble(aList[0]) < Double.parseDouble(aList[1])) ? 1 : 0;
		return returnVal;
	}

}