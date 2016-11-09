package back_end.model.syntax_tree;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.inner_nodes.AbstractInnerNode;
import back_end.model.node.leaf_nodes.AbstractLeafNode;

/**
 * A class to manage unmarking the subtree, given a node
 * This class depends on the Node classes
 * In order to mark all children of a given node as avaliable:
 * TreeCleaner cleaner = new TreeCleaner();
 * cleaner.markAllChildrenAsAvailable(aNode);
 * 
 * @author matthewfaw
 *
 */
public class TreeCleaner {
	public TreeCleaner()
	{
	}
	
	/**
	 * A method to, given a aNode, mark the node's subtree as Available
	 * The input node's state is not altered
	 * This method will throw an error if it tries to access children of a node when it should not
	 * @param aNode
	 * @throws InvalidNodeUsageException
	 */
	public void markAllChildrenAsAvailable(INode aNode) throws InvalidNodeUsageException
	{
		if (aNode instanceof AbstractInnerNode) {
			markAllChildrenAsAvailable((AbstractInnerNode) aNode);
		} else if (aNode instanceof AbstractLeafNode) {
			markAllChildrenAsAvailable((AbstractLeafNode) aNode);
		}
	}
	
	private void markAllChildrenAsAvailable(AbstractInnerNode aParentNode) throws InvalidNodeUsageException
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
