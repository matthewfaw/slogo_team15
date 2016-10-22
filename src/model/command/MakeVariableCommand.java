package model.command;

import model.exception.ArgumentException;

public class MakeVariableCommand implements ICommand {
	
	public MakeVariableCommand() {
		
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		if (aList.length != 2) {
			throw new ArgumentException("Method Evaluation needs two arguments");
		}
		return 0;
	}

}
