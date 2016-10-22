package model.node;

public class ConstantNode implements INode {
	
	private double myValue;
	private int myNumberOfInputs = 0;

	public ConstantNode(double aValue) {
		myValue = aValue;
	}
	
	@Override
	public double eval(String...aList) {
		return myValue;
	} 
	
	public int getNumberOfInputs() {
		return myNumberOfInputs;
	}

}
