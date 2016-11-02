package back_end.model.node.inner_nodes.command_nodes.branching_nodes;

import java.util.List;

import back_end.model.command.AskCommand;
import back_end.model.command.ICommand;
import back_end.model.command.ICommandBranch;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.inner_nodes.list_nodes.ListNode;
import back_end.model.states.ScopeController;

public class ControlFlowNode extends AbstractBranchNode {
	private static final int CONDITION_INDEX = 0;
	private ICommandBranch myCommand;
	private boolean myFirstTimeCalled;
	
	public ControlFlowNode(ICommand aCommand, int aNumberOfInputs, String aUserInput,
			ScopeController aScopeController) {
		super(aNumberOfInputs, aScopeController);

		myCommand = (ICommandBranch) aCommand;

		myFirstTimeCalled = true;
	}
	
	@Override
	protected ICommand getCommand()
	{
		return myCommand;
	}
	
	@Override
	public void eval() throws InvalidInputNumberException, InvalidNodeUsageException 
	{
		switch (getEvaluationState()) {
			case EVALUATING_INPUTS:
				//XXX: remove code call dependencies
				if (myFirstTimeCalled) {
					myFirstTimeCalled = false;

					constructScope();
				}
				super.evalCondition(myCommand);
				break;
			case EVALUATING_BRANCH:
				super.eval(myCommand);
				break;
			case EVALUATED:
				// do nothing
			default:
				throw new InvalidNodeUsageException("Node state" + getEvaluationState() + "is invalid!");
		}
	}
	private void constructScope()
	{
		super.getScopeController().addNestedScope();
		if (myCommand instanceof AskCommand) {
			super.getScopeController().addTemporaryTurtleScope();
		}
	}
	/**
	 * This method assumes that the first INode that
	 * it gets passed is the condition, and all following
	 * nodes are the branches
	 * @throws InvalidNodeUsageException 
	 */
	@Override
	public void setChildren(List<INode> aChildren) throws InvalidNodeUsageException {
		super.setInputs(aChildren.get(CONDITION_INDEX));
		
		for (int i=CONDITION_INDEX+1; i<aChildren.size(); ++i) {
			INode aNode = aChildren.get(i);
			if (aNode instanceof ListNode) {
				ListNode aListNode = (ListNode) aNode;
				super.setBranch(i - 1, aListNode);
			} else {
				throw new InvalidNodeUsageException("Attempting to add a branch that is not a ListNode!");
			}
		}
	}
}
