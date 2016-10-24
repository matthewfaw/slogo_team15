package model.node;

import java.util.ArrayList;
import java.util.List;

import model.command.ICommand;
import model.exception.ArgumentException;
import model.states.Scope;

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
	public double eval() throws ArgumentException {
		if (super.getChildren().size() != myNumberOfInputs) {
			throw new ArgumentException("Invalid number of arguments"); 
		}
		Node[] inputList = super.getChildren().toArray(new Node[super.getChildren().size()]);
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
