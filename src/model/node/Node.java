package model.node;

import java.util.ArrayList;
import java.util.List;

import model.exception.ArgumentException;

public abstract class Node implements IReadableInput {
	private ArrayList<Node> myChildren;
	
	protected Node()
	{
		myChildren = new ArrayList<Node>();
	}
	public List<Node> getChildren()
	{
		return myChildren;
	}
	public void addChild(Node aNode)
	{
		myChildren.add(aNode);
	}
	
	public abstract double eval(List<Node> aList) throws ArgumentException;
	
//	public abstract List<Node> getChildren();
//	public abstract void addChild(Node aNode);
	
	public abstract NodeState getState();
	public abstract void setState(NodeState aNodeState);

}
