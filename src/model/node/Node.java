package model.node;

import java.util.ArrayList;
import java.util.List;

import model.exception.ArgumentException;

public abstract class Node implements IReadableInput {
	private ArrayList<Node> myChildren;
	private NodeState myState;
	
	protected Node()
	{
		myChildren = new ArrayList<Node>();
		
		myState = NodeState.AVAILABLE;
	}
	public List<Node> getChildren()
	{
		return myChildren;
	}
	public void addChild(Node aNode)
	{
		myChildren.add(aNode);
	}
	
	public NodeState getState()
	{
		return myState;
	}
	public void setState(NodeState aNodeState)
	{
		myState = aNodeState;
	}

	public abstract double eval() throws ArgumentException;
	
//	public abstract List<Node> getChildren();
//	public abstract void addChild(Node aNode);
	
}
