package back_end.model.node.inner_nodes;

import java.util.List;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;

public abstract class AbstractInnerNode implements INode {
	private NodeState myState;
	
	protected AbstractInnerNode()
	{
		myState = NodeState.AVAILABLE;
	}

	public abstract List<INode> getChildren() throws InvalidNodeUsageException;
	public abstract void setChildren(List<INode> aChildren) throws InvalidNodeUsageException;
	
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
