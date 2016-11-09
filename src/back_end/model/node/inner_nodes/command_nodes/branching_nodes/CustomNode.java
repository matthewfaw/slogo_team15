package back_end.model.node.inner_nodes.command_nodes.branching_nodes;

import java.util.List;

import back_end.model.command.CustomCommand;
import back_end.model.command.ICommand;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.inner_nodes.list_nodes.ListNode;
import back_end.model.states.ScopeController;

/**
 * A class that represents the behavior necessary in a custom node
 * Once constructed, this class assumes that it's CustomCommand class
 * provides a valid subtree to evaluate
 * This class depends on the CustomCommand class to retrieve the branch
 * corresponding to things to be evaluated by the function
 * It also depends on the scope controller to properly change scopes 
 * when entering and exiting function calls
 * 
 * @author matthewfaw
 *
 */
public class CustomNode extends AbstractBranchNode {
	private static final int CONDITION_INDEX = 0;

	private CustomCommand myCommand;
	private boolean myFirstTimeCalled;

	public CustomNode(ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
		super(aNumberOfInputs, aScopeController);
		
		myCommand = (CustomCommand) aCommand;
		myFirstTimeCalled = true;
	}
	
	@Override
	protected ICommand getCommand()
	{
		return myCommand;
	}
	
	@Override
	public void eval() throws InvalidInputNumberException, InvalidNodeUsageException {

		switch (getEvaluationState()) {
			case EVALUATING_INPUTS:
				if (myFirstTimeCalled) {
					super.getScopeController().addNewFunctionScope();
				}
				super.evalCondition(myCommand);
				if (super.hasActiveBranch()) {
					initializeBranch();
				} 
				break;
			case EVALUATING_BRANCH:
				super.eval(myCommand);
				super.getScopeController().removeCurrentFunctionScope();
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
	
	/**
	 * A method used to retrieve the function body and add it to the AST
	 * assumes that the command.getFunction returns a list node
	 * @throws InvalidNodeUsageException
	 */
	private void initializeBranch() throws InvalidNodeUsageException
	{
		if (super.hasActiveBranch()) {
			ListNode methodBody = (ListNode) myCommand.getFunction();
			methodBody.setState(NodeState.AVAILABLE);
			super.setBranch(0, (ListNode) myCommand.getFunction());
		} 
	}

}
