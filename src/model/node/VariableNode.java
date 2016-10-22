package model.node;

import model.states.VariableState;

public class VariableNode implements INode {
	
	private String myName;
	private double myValue;
	private int myNumberOfInputs = 0;
	
	public VariableNode(String aVariable, VariableState aState) {
		myName = aVariable;
		myValue = -1; 
	}

	@Override
	public double eval(String...aList) {
		return myValue;
	}
	
	public int getNumberOfInputs() {
		return myNumberOfInputs;
	}

}
