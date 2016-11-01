package back_end.model.node.inner_nodes.command_nodes.input_nodes;

import java.util.ArrayList;
import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.inner_nodes.command_nodes.branching_nodes.AbstractBranchNode;
import back_end.model.node.inner_nodes.list_nodes.ListNode;
import back_end.model.node.leaf_nodes.VariableNode;
import back_end.model.states.Environment;
import back_end.model.states.ScopeController;

public class CommandDefinitionNode extends AbstractInputCommandNode {
	private static final int NAME_INDEX = 0;
	private static final int INPUTS_INDEX = 1;
	private static final int METHOD_BODY_INDEX = 2;
	public CommandDefinitionNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
		super(aCommand, aNumberOfInputs);
	}
	/**
	 * This method assumes that the first INode that
	 * it gets passed is the command name, the second is
	 * list of inputs, and all following
	 * nodes are the branches
	 * @throws InvalidNodeUsageException 
	 */
	@Override
	public void setChildren(List<INode> aChildren) throws InvalidNodeUsageException {
		INode methodName = aChildren.get(NAME_INDEX);
		ListNode methodInputNames = getListNode(aChildren.get(INPUTS_INDEX));
		ListNode methodBody = getListNode(aChildren.get(METHOD_BODY_INDEX));
		//XXX: Must initially mark this node as visited so that the method
		// definition body doesn't get evaluated
		methodBody.setState(NodeState.VISITED);
		
		
		super.addChild(methodName);
		super.addChildren(methodInputNames.getChildren());
		super.addChild(methodBody);
	}
	private ListNode getListNode(INode aNode) throws InvalidNodeUsageException
	{
		if (aNode instanceof ListNode) {
			ListNode aListNode = (ListNode) aNode;
			return aListNode;
		} else {
			throw new InvalidNodeUsageException("Attempting to add a branch that is not a ListNode!");
		}
		
	}
}
