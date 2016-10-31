package back_end.model.node.inner_nodes.command_nodes.branching_nodes;

import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.inner_nodes.list_nodes.ListNode;
import back_end.model.states.ScopeController;

public class CustomNode extends AbstractBranchNode {
	private static final int CONDITION_INDEX = 0;

	private ScopeController myScopeController;  

	public CustomNode(ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
		super(aCommand, aNumberOfInputs);
		
		myScopeController = aScopeController;
	}

	@Override
	public void setChildren(List<INode> aChildren) throws InvalidNodeUsageException 
	{
		super.setInputs(aChildren.get(CONDITION_INDEX));

		//TODO: figure out how to set up the branch child
		
//		for (int i=CONDITION_INDEX+1; i<aChildren.size(); ++i) {
//			INode aNode = aChildren.get(i);
//			if (aNode instanceof ListNode) {
//				ListNode aListNode = (ListNode) aNode;
//				super.setBranch(i, aListNode);
//			} else {
//				throw new InvalidNodeUsageException("Attempting to add a branch that is not a ListNode!");
//			}
//		}
	}

}
