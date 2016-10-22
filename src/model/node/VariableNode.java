package model.node;

import model.states.VariableState;

public class VariableNode implements INode {
	
	private String myName;
	private int myNumberOfInputs = 0;
	private VariableState myVariableState;
	
	public VariableNode(String aVariable, VariableState aVariableState) {
		myName = aVariable;
		myVariableState = aVariableState;
	}

	@Override
	public double eval(String...aList) {
		return myVariableState.getValue(myName);
	}
	
	public String getName() {
		return myName;
	}
	
	public int getNumberOfInputs() {
		return myNumberOfInputs;
	}

}
