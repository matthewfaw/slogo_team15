package model.command.turtleCommands;

import model.command.ICommand;
import model.exception.ArgumentException;

public abstract class BooleanTurtleCommand implements ICommand {
	
	
	public BooleanTurtleCommand() {
	}

	public void errorCheck(String... aList) throws ArgumentException {
		if (aList.length != 0) {
			throw new ArgumentException("Method Evaluation needs zero arguments");
		}
	}

}