package back_end.model.syntax_tree;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.dummy_nodes.NullNode;
import back_end.model.node.inner_nodes.AbstractInnerNode;
import back_end.model.node.leaf_nodes.AbstractLeafNode;

/**
 * A class that manages traversing the AST to find the next available node
 * Assumes that nodes are evaluated in a Depth first fashion
 * This class is a loose interpretation of the visitor pattern applied to the AST,
 * since it operates on the AST but does not own it
 * 
 * In order to use this class:
 * AvailableNodeFinder finder = new AvailableNodeFinder();
 * To get the next unvisited child node, given aParentNode:
 * finder.getNextUnvisitedChild(aParenNode);
 * 
 * @author matthewfaw
 *
 */
class AvailableNodeFinder {
	AvailableNodeFinder()
	{
	}
	
	/**
	 * This method finds the next unvisited child node of aParentNode
	 * Assumes that aParenNode is not null
	 * @param aParentNode
	 * @return the next unvisited child node if the parent hasn't already been visited, otherwise returns a NullNode
	 * @throws InvalidNodeUsageException
	 */
	INode getNextUnvisitedChild(INode aParentNode) throws InvalidNodeUsageException
	{
		if (!aParentNode.isVisited()) {
			if (aParentNode instanceof AbstractLeafNode) {
				return getNextUnvisitedChild((AbstractLeafNode) aParentNode);
			} else if (aParentNode instanceof AbstractInnerNode) {
				return getNextUnvisitedChild((AbstractInnerNode) aParentNode);
			}
		}
		
		return new NullNode();
	}
	private INode getNextUnvisitedChild(AbstractInnerNode aNode) throws InvalidNodeUsageException 
	{
		if (aNode.getChildren() == null) return new NullNode();
		for (INode child: aNode.getChildren()) {
			if (!child.isVisited()) {
				return child;
			}
		}
		
		return new NullNode();
	}		
	private INode getNextUnvisitedChild(AbstractLeafNode aNode) throws InvalidNodeUsageException
	{
		if (!aNode.isVisited()) {
			return aNode;
		} else {
			return new NullNode();
		}
	}
}
