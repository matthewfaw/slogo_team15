package model.node;


public class ConstantNode implements Node {
	
	private double myValue;
	private int myNumberOfInputs = 0;

	public ConstantNode(double aValue) {
		myValue = aValue;
	}
	
	@Override
	public double eval(IReadableInput...aList) {
		return myValue;
	} 
	
	public int getNumberOfInputs() {
		return myNumberOfInputs;
	}

}
