package back_end.model.node.inner_nodes;

import java.util.List;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;

/**
 * This class represents the default functionality of Inner nodes of the AST
 * Thus, it is assumed that inner nodes have children
 * @author matthewfaw
 *
 */
public abstract class AbstractInnerNode implements INode {
	private NodeState myState;
	
	protected AbstractInnerNode()
	{
		myState = NodeState.AVAILABLE;
	}

	/**
	 * A method that allows us to access the children of a given node
	 * @return the list of currently active children
	 * @throws InvalidNodeUsageException
	 */
	public abstract List<INode> getChildren() throws InvalidNodeUsageException;
	/**
	 * A method that sets the current list of children to the input list of children aChildren
	 * @param aChildren
	 * @throws InvalidNodeUsageException
	 */
	public abstract void setChildren(List<INode> aChildren) throws InvalidNodeUsageException;
	
	/**
	 * A method to check if all children of a given node have been evaluated
	 * Assumes that, if there are no children, that the method returns true
	 * @return true if all children have been evaluated, false otherwise
	 * @throws InvalidNodeUsageException
	 */
	public boolean allChildrenAreEvaluated() throws InvalidNodeUsageException
	{
		if (getChildren() == null) return true;
		for (INode child: getChildren()) {
			if (!child.isVisited()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public NodeState getState() {
		return myState;
	}
	
	@Override
	public boolean isVisited()
	{
		return myState == NodeState.VISITED;
	}


	@Override
	public void setState(NodeState aNodeState) throws InvalidNodeUsageException {
		myState = aNodeState;
	}

	@Override
	public String getName() throws InvalidNodeUsageException {
		throw new InvalidNodeUsageException("InnerNodes nodes do not have a name");
	}
}
