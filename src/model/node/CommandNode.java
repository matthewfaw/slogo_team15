package model.node;

import java.util.ArrayList;

import model.command.ICommand;
import model.exception.ArgumentException;
import model.states.VariableState;

public class CommandNode implements INode {
	
	private ICommand myCommand;
	private int myNumberOfInputs;
	private ArrayList<INode> myChildren;
	
	public CommandNode(ICommand aCommand, int aNumberOfInputs, VariableState aVariableStates) {
		myCommand = aCommand;
		myNumberOfInputs = aNumberOfInputs;
		myChildren = new ArrayList<INode>(); 
	}

	@Override
	public double eval(String...aList) throws ArgumentException {
		if (aList.length != myNumberOfInputs) {
			throw new ArgumentException("Invalid number of arguments"); 
		}
		return myCommand.eval(aList);
	}
	
	public void addChildren(INode...aList) {
		for (int i = 0; i < aList.length; i++) {
			myChildren.add(aList[i]);
		}
	}
	
	public int getNumberOfInputs() {
		return myNumberOfInputs;
	}

}
