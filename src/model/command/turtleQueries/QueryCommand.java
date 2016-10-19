package model.command.turtleQueries;

import model.command.ICommand;
import model.exception.ArgumentException;

public abstract class QueryCommand implements ICommand {
	
	public void errorCheck(String...aList) throws ArgumentException {
		if (aList.length != 0) {
			throw new ArgumentException("Method Evaluation needs zero arguments");
		}
	}

}
