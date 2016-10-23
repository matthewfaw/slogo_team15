package model.node;

import java.util.List;

import model.exception.ArgumentException;
import model.states.Scope;

public class VariableNode extends ValueNode {
	
	private String myName;
	private int myNumberOfInputs = 0;
	private Scope myScope;
	private double myValue;
	
	public VariableNode(String aVariable, Scope aScope) {
		super();

		myName = aVariable;
		myScope = aScope;
		myValue = myScope.getValue(myName);
	}

	@Override
	public double eval(List<Node> aList) throws ArgumentException {
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
}
