package back_end.model.node.inner_nodes.list_nodes;

import java.util.ArrayList;
import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.inner_nodes.AbstractInnerNode;
import back_end.model.states.ScopeController;


// XXX: Add stuff
public class ListNode extends AbstractInnerNode {

	//    public ListNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
	//    }
	private List<INode> myChildren;

	public ListNode()
	{
		super();

		myChildren = new ArrayList<INode>();
	}

	@Override
	public void eval() throws ArgumentException {
		// do nothing
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

}
