package model.node;

import model.command.ICommand;
import model.exception.ArgumentException;

public class MethodNode implements INode {
	
	private ICommand myCommand;
	private int myNumberOfInputs;
	
	public MethodNode(ICommand aCommand, int aNumberOfInputs) {
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
