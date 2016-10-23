package model.node;

import java.util.List;

import model.exception.ArgumentException;

public class ConstantNode extends Node {
	
	private double myValue;
	private int myNumberOfInputs = 0;

	public ConstantNode(double aValue) {
		myValue = aValue;
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
