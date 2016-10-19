package model.command.mathOperations;

import model.command.ICommand;
import model.exception.ArgumentException;

public class PiCommand implements ICommand {
	
	public PiCommand() {
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		if (aList.length != 0) {
			throw new ArgumentException("Method Evaluation needs zero arguments");
		}
		return Math.PI;
	}

}