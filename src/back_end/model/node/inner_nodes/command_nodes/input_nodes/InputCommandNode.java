package back_end.model.node.inner_nodes.command_nodes.input_nodes;

import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.inner_nodes.list_nodes.ListNode;
import back_end.model.states.ScopeController;

/**
 * The purpose of this class is to provide a default way of managing
 * children for command nodes
 * @author matthewfaw
 *
 */
public class InputCommandNode extends AbstractInputCommandNode {

	public InputCommandNode(ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) 
	{
		super(aCommand, aNumberOfInputs, aScopeController);
	}

	@Override
	public void setChildren(List<INode> aChildren) throws InvalidNodeUsageException 
	{
		if (aChildren.size() == 0) return;
		//XXX: kinda hacky, should remove this
		if (aChildren.get(0) instanceof ListNode) {
			ListNode listNode = (ListNode) aChildren.get(0);
			super.addChildren(listNode.getChildren());
		} else {
			super.addChildren(aChildren);
		}
	}

}
