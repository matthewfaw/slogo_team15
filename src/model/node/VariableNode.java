package model.node;

import model.states.Scope;

public class VariableNode implements INode, IReadableInput {
	
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
	public double eval(IReadableInput...aList) {
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

}
