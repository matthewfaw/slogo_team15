package back_end.model.node.inner_nodes.command_nodes.branching_nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import back_end.model.command.AskCommand;
import back_end.model.command.ICommand;
import back_end.model.command.ICommandBranch;
import back_end.model.command.ICommandTurtle;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.EvaluationState;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.inner_nodes.command_nodes.AbstractCommandNode;
import back_end.model.node.inner_nodes.list_nodes.ListNode;
import back_end.model.states.ScopeController;
import back_end.model.syntax_tree.TreeCleaner;

public abstract class AbstractBranchNode extends AbstractCommandNode {
    private EvaluationState myEvaluationState;
    private List<INode> myChildInputs;
    private HashMap<Integer, List<INode>> myChildBranches;

	private double myReturnValue;
	private int myActiveBranchIndex;
	
	private TreeCleaner myCleaner;

    protected AbstractBranchNode (int aNumberOfInputs, ScopeController aScopeController) {
        super(aNumberOfInputs, aScopeController);

        myEvaluationState = EvaluationState.EVALUATING_INPUTS;

        myChildInputs = new ArrayList<INode>();
        myChildBranches = new HashMap<Integer, List<INode>>();
        
        myCleaner = new TreeCleaner();
    }
    
    protected EvaluationState getEvaluationState()
    {
    	return myEvaluationState;
    }
	
	protected void evalCondition(ICommandBranch aCommand) throws InvalidInputNumberException, InvalidNodeUsageException
	{
		INode[] inputs;
		inputs = super.convertListToProperInputForm(myChildInputs);
		myActiveBranchIndex = aCommand.evalCondition(inputs);
		if (hasActiveBranch()) {
			myEvaluationState = EvaluationState.EVALUATING_BRANCH;
			
			myCleaner.markAllChildrenAsAvailable(this);
		} else {
			myEvaluationState = EvaluationState.EVALUATED;
			super.setState(NodeState.VISITED);
			
			resetStatesForNewTurtle();
//			super.register();
//			super.triggerUpdatesToNodesForNewlyActiveTurtle();
		}
	}
	protected void eval(ICommandBranch aCommand) throws InvalidInputNumberException, InvalidNodeUsageException
	{
		INode[] inputs;

		inputs = super.convertListToProperInputForm(myChildBranches.get(myActiveBranchIndex));
		myReturnValue = aCommand.eval(inputs);
		myEvaluationState = EvaluationState.EVALUATING_INPUTS;
	}

	@Override
	public double getValue() throws InvalidNodeUsageException {
		if (myEvaluationState != EvaluationState.EVALUATED) {
			throw new InvalidNodeUsageException("Attempt to access node value before it is ready!");
		}
		
		return myReturnValue;
	}

	@Override
	public List<INode> getChildren() throws InvalidNodeUsageException {
		switch (myEvaluationState) {
			case EVALUATING_INPUTS:
				return myChildInputs;
			case EVALUATING_BRANCH:
				return myChildBranches.get(myActiveBranchIndex);
			default:
				throw new InvalidNodeUsageException("Attempt to access children after node has been evaluated");
		}
	}

	protected boolean hasActiveBranch()
	{
		return myActiveBranchIndex != -1;
	}

    protected void setInputs(INode aInputs) throws InvalidNodeUsageException
    {
    	if (aInputs instanceof ListNode) {
    		ListNode branch = (ListNode) aInputs;
    		myChildInputs.addAll(branch.getChildren());
    	} else {
    		myChildInputs.add(aInputs);
    	}
    }
    protected void setBranch(int aBranchIndex, ListNode aInputs) throws InvalidNodeUsageException
    {
    	if (myChildBranches.containsKey(aBranchIndex)) {
    		throw new InvalidNodeUsageException("Overwriting a branch that already exists!");
    	}
    	
    	myChildBranches.put(aBranchIndex, aInputs.getChildren());
    }
    	
	@Override
    protected void resetStatesForNewTurtle() throws InvalidNodeUsageException
    {
		if (getCommand() instanceof ICommandTurtle) {
			getScopeController().setNextTurtleAsActive();
			if (!getScopeController().activeTurtleIndexHasBeenSetToStart()) {
				resetStates();
			} else {
				destructScope();
			}
		} else {
			super.getScopeController().removeNestedScope();
		}
    }
	private void destructScope()
	{
		super.getScopeController().removeNestedScope();
		if (getCommand() instanceof AskCommand) {
			super.getScopeController().removeTemporaryTurtleScope();
		}
	}
	
	@Override
	public void resetStates() throws InvalidNodeUsageException
	{
		super.setState(NodeState.AVAILABLE);
		myEvaluationState = EvaluationState.EVALUATING_BRANCH;
	}
}
