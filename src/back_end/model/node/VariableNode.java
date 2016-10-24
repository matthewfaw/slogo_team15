package back_end.model.node;

import java.util.List;

import back_end.model.exception.ArgumentException;
import back_end.model.states.Scope;

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
	public double eval() throws ArgumentException {
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
