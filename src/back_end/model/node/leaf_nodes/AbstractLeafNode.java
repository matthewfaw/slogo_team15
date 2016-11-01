package back_end.model.node.leaf_nodes;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.states.ScopeController;

public abstract class AbstractLeafNode implements INode {
	private NodeState myNodeState;

    protected AbstractLeafNode() {
    	myNodeState = NodeState.AVAILABLE;
    }

	@Override
	public NodeState getState() {
		return myNodeState;
	}
	
	@Override
	public boolean isVisited()
	{
		return myNodeState == NodeState.VISITED;
	}

	@Override
	public void setState(NodeState aNodeState) throws InvalidNodeUsageException {
		myNodeState = aNodeState;
	}
}
