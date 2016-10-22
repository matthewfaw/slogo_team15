package model.command;

import model.exception.ArgumentException;

public class RandomCommand implements ICommand {
	
	public RandomCommand() {
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		if (aList.length != 1) {
			throw new ArgumentException("Method Evaluation needs one argument");
		}
		return Math.random() * Double.parseDouble(aList[0]);
	}

}