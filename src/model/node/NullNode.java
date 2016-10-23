package model.node;

import java.util.List;

import model.exception.ArgumentException;

public class NullNode extends Node {
	
	public NullNode()
	{
		super();
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public double getValue() {
		return 0;
	}

	@Override
	public double eval(List<Node> aList) throws ArgumentException {
		// TODO Auto-generated method stub
		return 0;
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
