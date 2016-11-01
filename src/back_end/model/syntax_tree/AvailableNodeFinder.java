package back_end.model.syntax_tree;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.dummy_nodes.NullNode;
import back_end.model.node.inner_nodes.AbstractInnerNode;
import back_end.model.node.leaf_nodes.AbstractLeafNode;

class AvailableNodeFinder {
	AvailableNodeFinder()
	{
	}
	
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
