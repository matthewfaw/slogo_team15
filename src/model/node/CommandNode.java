package model.node;

import java.util.ArrayList;

import model.command.ICommand;
import model.exception.ArgumentException;
import model.states.Scope;

public class CommandNode implements INode, IReadableInput {
	
	private ICommand myCommand;
	private int myNumberOfInputs;
	private ArrayList<INode> myChildren;
	private double myOutput;
	
	public CommandNode(ICommand aCommand, int aNumberOfInputs, Scope aScope) {
		myCommand = aCommand;
		myNumberOfInputs = aNumberOfInputs;
		myChildren = new ArrayList<INode>(); 
	}

	@Override
	public double eval(IReadableInput...aList) throws ArgumentException {
		if (aList.length != myNumberOfInputs) {
			throw new ArgumentException("Invalid number of arguments"); 
		}
		myOutput = myCommand.eval(aList);
		return myOutput;
	}
	
	public void addChildren(INode...aList) {
		for (int i = 0; i < aList.length; i++) {
			myChildren.add(aList[i]);
		}
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
