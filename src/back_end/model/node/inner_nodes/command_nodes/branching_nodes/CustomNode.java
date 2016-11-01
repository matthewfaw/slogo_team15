package back_end.model.node.inner_nodes.command_nodes.branching_nodes;

import java.util.List;

import back_end.model.command.CustomCommand;
import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.inner_nodes.AbstractInnerNode;
import back_end.model.node.inner_nodes.list_nodes.ListNode;
import back_end.model.states.ScopeController;

public class CustomNode extends AbstractBranchNode {
	private static final int CONDITION_INDEX = 0;

	private ScopeController myScopeController;  
	private CustomCommand myCommand;

	public CustomNode(ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
		super(aNumberOfInputs);
		
		myScopeController = aScopeController;
		myCommand = (CustomCommand) aCommand;
	}

	@Override
	public void eval() throws ArgumentException, InvalidNodeUsageException {

		switch (getEvaluationState()) {
			case EVALUATING_INPUTS:
				super.evalCondition(myCommand);
				initializeBranch();
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

	@Override
	public void setChildren(List<INode> aChildren) throws InvalidNodeUsageException 
	{
		super.setInputs(aChildren.get(CONDITION_INDEX));
		//Note that the actual method body doesn't get set until the method's evalCondition is called
	}
	
	private void initializeBranch() throws InvalidNodeUsageException
	{
		ListNode methodBody = (ListNode) myCommand.getFunction();
		methodBody.setState(NodeState.AVAILABLE);
		super.setBranch(0, (ListNode) myCommand.getFunction());
	}
	
}
