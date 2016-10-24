package model.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.command.ICommand;
import model.exception.ArgumentException;
import model.states.Scope;

public class BranchNode extends Node {
	private NodeState myEvaluationState;
	
	private ArrayList<Node> myChildConditions;
	private HashMap<Integer, List<Node>> myChildBranches;
	
	private int myActiveBranchIndex;
	
	private double myReturnValue;
	private int myConditionReturnValue;
	
	private int myNumberOfInputs;
	private ICommandBranch myCommand;


	public BranchNode(ICommandBranch aCommand, int aNumberOfInputs, Scope aScope) {
		super();

		myEvaluationState = NodeState.EVALUATING_CONDITION;
		
		myChildConditions = new ArrayList<Node>();
		myChildBranches = new HashMap<Integer, List<Node>>();
		
		myNumberOfInputs = aNumberOfInputs;
		myCommand = aCommand;
	}
	
	public int getNumberOfInputs()
	{
		return myNumberOfInputs;
	}
	
	public NodeState getEvaluationState()
	{
		return myEvaluationState;
	}
	public void setEvaluationState(NodeState aNodeState)
	{
		myEvaluationState = aNodeState;
	}
	
	@Override
	public double eval() throws ArgumentException {
		myReturnValue = myCommand.eval(myChildBranches.get(myActiveBranchIndex));
		return myReturnValue;
	}
	public int evalCondition() {
		myConditionReturnValue = myCommand.evalCommand(myChildConditions);
//		myEvaluationState = NodeState.EVALUATING_BRANCH;
		myActiveBranchIndex = myConditionReturnValue;
		return myConditionReturnValue;
	}
	public int getConditionEvaluation()
	{
		return myConditionReturnValue;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Node> getChildren() {
		switch (myEvaluationState) {
		case EVALUATING_CONDITION:
			return myChildConditions;
		case EVALUATING_BRANCH:
			return myChildBranches.get(myActiveBranchIndex);
		default:
			//XXX: Change to throw an error;
			return null;
		}
	}
	
	@Override
	public void addChild(Node aNode) {
		//XXX: remove this
		throw new RuntimeException("Do not use this method");
	}
	public void addConditions(ListNode aList)
	{
		for (Node child: aList.getChildren()) {
			myChildConditions.add(child);
		}
	}
	public void addBranchChildren(int aBranchId, ListNode aListNode)
	{
		if(!myChildBranches.containsKey(aBranchId)) {
			myChildBranches.put(aBranchId, new ArrayList<Node>());
		}
		for (Node child: aListNode.getChildren()) {
			myChildBranches.get(aBranchId).add(child);
		}
	}

}
