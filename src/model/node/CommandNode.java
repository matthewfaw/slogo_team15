package model.node;

import java.util.ArrayList;
import java.util.List;

import model.command.ICommand;
import model.exception.ArgumentException;
import model.states.Scope;

public class CommandNode extends Node implements IReadableInput {
	
	private ICommand myCommand;
	private int myNumberOfInputs;
	private ArrayList<Node> myChildren;
	private double myOutput;
	
	public CommandNode(ICommand aCommand, int aNumberOfInputs, Scope aScope) {
		myCommand = aCommand;
		myNumberOfInputs = aNumberOfInputs;
		myChildren = new ArrayList<Node>(); 
	}

	@Override
	public double eval(List<IReadableInput> aList) throws ArgumentException {
		if (aList.size() != myNumberOfInputs) {
			throw new ArgumentException("Invalid number of arguments"); 
		}
		myOutput = myCommand.eval(aList);
		return myOutput;
	}
	
	@Override
	public void addChild(Node aNode) {
		myChildren.add(aNode);
	}

	@Override
	public List<Node> getChildren() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public NodeState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setState(NodeState aNodeState) {
		// TODO Auto-generated method stub
		
	}


}
