package back_end.model.node;

import java.util.List;

import back_end.model.exception.ArgumentException;

public class ConstantNode extends ValueNode {
	
	private double myValue;
	private int myNumberOfInputs;

	public ConstantNode(double aValue) {
		super();

		myValue = aValue;
		myNumberOfInputs = 0;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
