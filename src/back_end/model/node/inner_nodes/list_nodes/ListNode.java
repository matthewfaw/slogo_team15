package back_end.model.node.inner_nodes.list_nodes;

import java.util.ArrayList;
import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.inner_nodes.AbstractInnerNode;
import back_end.model.states.ScopeController;

/**
 * A class that represents a node whose children are expressions contained within the brackets
 * It is assumed that there is no value associated with these nodes
 * @author matthewfaw
 *
 */
public class ListNode extends AbstractInnerNode {

	private List<INode> myChildren;

	public ListNode()
	{
		super();

		myChildren = new ArrayList<INode>();
	}

	@Override
	public void eval() throws InvalidInputNumberException, InvalidNodeUsageException {
		super.setState(NodeState.VISITED);
	}

	@Override
	public double getValue() throws InvalidNodeUsageException {
		throw new InvalidNodeUsageException("List nodes do not have a value");
	}

	@Override
	public List<INode> getChildren() throws InvalidNodeUsageException
	{
		return myChildren;
	}

	@Override
	public void setChildren(List<INode> aChildren) {
		myChildren = aChildren;
	}

	@Override
	public void resetStates() throws InvalidNodeUsageException {
		super.setState(NodeState.AVAILABLE);
	}

}
