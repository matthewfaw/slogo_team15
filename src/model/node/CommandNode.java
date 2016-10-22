package model.node;

import model.command.ICommand;
import model.exception.ArgumentException;
import model.states.VariableState;

public class CommandNode implements INode {
	
	private ICommand myCommand;
	private int myNumberOfInputs;
	
	public CommandNode(ICommand aCommand, int aNumberOfInputs, VariableState aVariableStates) {
		myCommand = aCommand;
		myNumberOfInputs = aNumberOfInputs;
	}

	@Override
	public double eval(String...aList) {
		try {
			return myCommand.eval(aList);
		} catch (ArgumentException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getNumberOfInputs() {
		return myNumberOfInputs;
	}

}
