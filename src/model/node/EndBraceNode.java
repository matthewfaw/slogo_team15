package model.node;

import java.util.List;

import model.exception.ArgumentException;

public class EndBraceNode extends Node {
	
	public EndBraceNode()
	{
		super();
	}

	@Override
	public double eval(List<Node> aList) throws ArgumentException {
		// TODO Auto-generated method stub
		return 0;
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
	public NodeState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setState(NodeState aNodeState) {
		// TODO Auto-generated method stub
		
	}

	
}
