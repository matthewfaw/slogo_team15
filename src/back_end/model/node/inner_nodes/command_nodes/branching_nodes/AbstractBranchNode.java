package back_end.model.node.inner_nodes.command_nodes.branching_nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.command.ICommandBranch;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.EvaluationState;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.inner_nodes.command_nodes.AbstractCommandNode;
import back_end.model.node.inner_nodes.list_nodes.ListNode;

public abstract class AbstractBranchNode extends AbstractCommandNode {
    private EvaluationState myEvaluationState;
    private List<INode> myChildInputs;
    private HashMap<Integer, List<INode>> myChildBranches;

	private double myReturnValue;
	private int myActiveBranchIndex;

    protected AbstractBranchNode (int aNumberOfInputs) {
        super(aNumberOfInputs);

        myEvaluationState = EvaluationState.EVALUATING_INPUTS;

        myChildInputs = new ArrayList<INode>();
        myChildBranches = new HashMap<Integer, List<INode>>();
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
		} else {
			myEvaluationState = EvaluationState.EVALUATED;
			super.setState(NodeState.VISITED);
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

	private boolean hasActiveBranch()
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
    	
//    @Override
//    public double eval () throws ArgumentException {
//        List<INode> currentBranch = myChildBranches.get(myActiveBranchIndex);
//        INode[] inputList = currentBranch.toArray(new INode[currentBranch.size()]);
//        myReturnValue = myCommand.eval(inputList);
//        return myReturnValue;
//    }
//
//    public int evalCondition () {
//        INode[] inputList = myChildConditions.toArray(new INode[myChildConditions.size()]);
//        myConditionReturnValue = myCommand.evalCondition(inputList);
//        if (myConditionReturnValue == -1) {
//            myReturnValue = myConditionReturnValue;
//        }
//        // myEvaluationState = NodeState.EVALUATING_BRANCH;
//        myActiveBranchIndex = myConditionReturnValue;
//        return myConditionReturnValue;
//    }
//
//    @Override
//    public List<INode> getChildren () {
//        switch (myEvaluationState) {
//            case EVALUATING_INPUTS:
//                return myChildConditions;
//            case EVALUATING_BRANCH:
//                return myChildBranches.get(myActiveBranchIndex);
//            default:
//                // XXX: Change to throw an error;
//                return null;
//        }
//    }
//
//    @Override
//    public void addChild (INode aNode) {
//        // XXX: remove this
//        throw new RuntimeException("Do not use this method");
//    }
//
//    public void addConditions (INode aNode) {
//        if (aNode instanceof ListNode) {
//            addConditions((ListNode) aNode);
//        }
//        else {
//            myChildConditions.add(aNode);
//        }
//    }
//
//    private void addConditions (ListNode aList) {
//        for (INode child : aList.getChildren()) {
//            myChildConditions.add(child);
//        }
//    }
//
//    public void addBranchChildren (int aBranchId, ListNode aListNode) {
//        if (!myChildBranches.containsKey(aBranchId)) {
//            myChildBranches.put(aBranchId, new ArrayList<INode>());
//        }
//        for (INode child : aListNode.getChildren()) {
//            myChildBranches.get(aBranchId).add(child);
//        }
//    }
//
//    public void unmarkAllChildren () {
//        // Unmark all conditions
//        for (INode child : myChildConditions) {
//            child.setState(NodeState.AVAILABLE);
//            unmarkAllChildren(child);
//        }
//
//        // Unmark all branches
//        for (int branchId : myChildBranches.keySet()) {
//            for (INode child : myChildBranches.get(branchId)) {
//                child.setState(NodeState.AVAILABLE);
//                unmarkAllChildren(child);
//            }
//        }
//    }
//
//    private void unmarkAllChildren (INode aParent) {
//        if (aParent instanceof AbstractBranchNode) {
//            ((AbstractBranchNode) aParent).unmarkAllChildren();
//        }
//        else {
//            for (INode child : aParent.getChildren()) {
//                child.setState(NodeState.AVAILABLE);
//                unmarkAllChildren(child);
//            }
//        }
//    }

}
