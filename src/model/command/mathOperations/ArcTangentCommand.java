package model.command.mathOperations;

import model.command.ICommand;
import model.exception.ArgumentException;

public class ArcTangentCommand implements ICommand {
	
	public ArcTangentCommand() {
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		if (aList.length != 1) {
			throw new ArgumentException("Method Evaluation needs one argument");
		}
		return Math.atan(Double.parseDouble(aList[0]));
	}

}