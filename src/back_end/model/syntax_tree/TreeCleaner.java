package back_end.model.syntax_tree;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.inner_nodes.AbstractInnerNode;
import back_end.model.node.leaf_nodes.AbstractLeafNode;

public class TreeCleaner {
	public TreeCleaner()
	{
	}
	
	public void markAllChildrenAsAvailable(INode aNode) throws InvalidNodeUsageException
	{
		if (aNode instanceof AbstractInnerNode) {
			markAllChildrenAsAvailable((AbstractInnerNode) aNode);
		} else if (aNode instanceof AbstractLeafNode) {
			markAllChildrenAsAvailable((AbstractLeafNode) aNode);
		}
	}
	
	public void markAllChildrenAsAvailable(AbstractInnerNode aParentNode) throws InvalidNodeUsageException
	{
		if (aParentNode.getChildren() == null) {
			return;
		}
		for (INode child: aParentNode.getChildren()) {
			child.resetStates();

			markAllChildrenAsAvailable(child);
		}
	}
	private void markAllChildrenAsAvailable(AbstractLeafNode aLeaf) throws InvalidNodeUsageException
	{
		aLeaf.resetStates();
	}
}
