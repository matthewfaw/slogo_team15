package model.node;

import java.util.List;

import model.exception.ArgumentException;

public class NullNode extends Node {

	@Override
	public String getName() {
		return null;
	}

	@Override
	public double getValue() {
		return 0;
	}

	@Override
	public double eval(List<IReadableInput> aList) throws ArgumentException {
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
