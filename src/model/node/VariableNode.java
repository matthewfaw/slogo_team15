package model.node;

import java.util.List;

import model.exception.ArgumentException;
import model.states.Scope;

public class VariableNode extends Node {
	
	private String myName;
	private int myNumberOfInputs = 0;
	private Scope myScope;
	private double myValue;
	
	public VariableNode(String aVariable, Scope aScope) {
		myName = aVariable;
		myScope = aScope;
		myValue = myScope.getValue(myName);
	}

	@Override
	public double eval(List<IReadableInput> aList) throws ArgumentException {
		// TODO Auto-generated method stub
		return myValue;
	}
	
	public int getNumberOfInputs() {
		return myNumberOfInputs;
	}
	
	@Override
	public String getName() {
		return myName;
	}

	@Override
	public double getValue() {
		return myValue;
	}

	@Override
	public List<Node> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addChild(Node aNode) {
		// TODO Auto-generated method stub
		
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
