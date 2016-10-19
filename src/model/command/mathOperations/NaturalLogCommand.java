package model.command.mathOperations;

import model.command.ICommand;
import model.exception.ArgumentException;

public class NaturalLogCommand implements ICommand {
	
	public NaturalLogCommand() {
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		if (aList.length != 1) {
			throw new ArgumentException("Method Evaluation needs one argument");
		}
		return Math.log(Double.parseDouble(aList[0]));
	}

}