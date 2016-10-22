package model.command;

import model.exception.ArgumentException;

public class DifferenceCommand implements ICommand {
	
	public DifferenceCommand() {
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		if (aList.length != 2) {
			throw new ArgumentException("Method Evaluation needs two arguments");
		}
		return Double.parseDouble(aList[0]) - Double.parseDouble(aList[1]);
	}

}