package model.node;

import java.util.List;

import model.exception.ArgumentException;

public abstract class Node implements IReadableInput {
	
	public abstract double eval(List<Node> aList) throws ArgumentException;
	
	public abstract List<Node> getChildren();
	public abstract void addChild(Node aNode);
	
	public abstract NodeState getState();
	public abstract void setState(NodeState aNodeState);

}
