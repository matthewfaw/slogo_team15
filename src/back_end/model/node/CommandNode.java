package back_end.model.node;

import java.util.ArrayList;
import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.states.Scope;

public class CommandNode extends Node {
	
	private ICommand myCommand;
	private int myNumberOfInputs;
	private double myOutput;
	
	public CommandNode(ICommand aCommand, int aNumberOfInputs, Scope aScope) {
		super();
		
		myCommand = aCommand;
		myNumberOfInputs = aNumberOfInputs;
	}

	@Override
	public double eval(List<Node> aList) throws ArgumentException {
		if (aList.size() != myNumberOfInputs) {
			throw new ArgumentException("Invalid number of arguments"); 
		}
		Node[] inputList = aList.toArray(new Node[aList.size()]);
		myOutput = myCommand.eval(inputList);
		return myOutput;
	}
	
	public int getNumberOfInputs() {
		return myNumberOfInputs;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public double getValue() {
		return myOutput;
	}

}
