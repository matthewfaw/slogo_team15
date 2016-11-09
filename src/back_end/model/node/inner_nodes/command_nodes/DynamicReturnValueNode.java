package back_end.model.node.inner_nodes.command_nodes;

import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.states.ScopeController;

/**
 * A node that represents the nodes containing values that are turtle-dependent
 * This class assumes that the node should re-call eval each time the node value is
 * requested.  This is to ensure that the most up-to-date value is always being 
 * retrieved for the currently active turtle
 * @author matthewfaw
 *
 */
public class DynamicReturnValueNode extends AbstractCommandNode {
	private ICommand myCommand;

	public DynamicReturnValueNode(ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController)
	{
		super(aNumberOfInputs, aScopeController);
		
		myCommand = aCommand;
	}
	
	@Override
	public void eval() throws InvalidInputNumberException, InvalidNodeUsageException {
        myCommand.eval();
        super.setState(NodeState.VISITED);
	}

	@Override
	public void resetStates() throws InvalidNodeUsageException {
		super.setState(NodeState.AVAILABLE);
	}

	@Override
	public double getValue() throws InvalidNodeUsageException {
		return myCommand.eval();
	}

	@Override
	protected ICommand getCommand() {
		return myCommand;
	}

	@Override
	protected void resetStatesForNewTurtle() throws InvalidNodeUsageException {
		//do nothing
	}

	@Override
	public List<INode> getChildren() throws InvalidNodeUsageException {
		return null;
	}

	@Override
	public void setChildren(List<INode> aChildren) throws InvalidNodeUsageException {
		//do nothing
	}

}
