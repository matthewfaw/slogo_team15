package back_end.model.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.states.Scope;

public class BranchNode extends Node {
	private NodeState myEvaluationState;
	
	private ArrayList<Node> myChildConditions;
	private HashMap<Integer, List<Node>> myChildBranches;
	
	private int myActiveBranchIndex;
	
	private double myReturnValue;
	private boolean myConditionReturnValue;

	public BranchNode(ICommand aCommand, int aNumberOfInputs, Scope aScope) {
		super();

		myEvaluationState = NodeState.EVALUATING_CONDITION;
		
		myChildConditions = new ArrayList<Node>();
		myChildBranches = new HashMap<Integer, List<Node>>();
	}
	
	public NodeState getEvaluationState()
	{
		return myEvaluationState;
	}
	
	@Override
	public double eval(List<Node> aList) throws ArgumentException {
		myReturnValue = 0;
		return myReturnValue;
	}
	public boolean evalCondition(List<Node> aList) {
		myConditionReturnValue = false;
		return myConditionReturnValue ;
	}
	public boolean getConditionEvaluation()
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
		//XXX: probably remove this
		throw new RuntimeException();
	}
	public void addCondition(Node aConditionNode)
	{
		myChildConditions.add(aConditionNode);
	}
	public void addBranchChild(int aBranchId, Node aChild)
	{
		if(!myChildBranches.containsKey(aBranchId)) {
			myChildBranches.put(aBranchId, new ArrayList<Node>());
		}
		myChildBranches.get(aBranchId).add(aChild);
	}

}
