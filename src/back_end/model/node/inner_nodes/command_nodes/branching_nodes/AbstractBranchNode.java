package back_end.model.node.inner_nodes.command_nodes.branching_nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.command.ICommandBranch;
import back_end.model.exception.ArgumentException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.inner_nodes.command_nodes.AbstractCommandNode;
import back_end.model.node.inner_nodes.list_nodes.ListNode;
import back_end.model.states.ScopeController;


public abstract class AbstractBranchNode extends AbstractCommandNode {
    private NodeState myEvaluationState;

    private ArrayList<INode> myChildConditions;
    private HashMap<Integer, List<INode>> myChildBranches;

    private int myActiveBranchIndex;

    private double myReturnValue;
    private int myConditionReturnValue;

    private int myNumberOfInputs;
    private ICommandBranch myCommand;

    public AbstractBranchNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
        super(aCommand, aNumberOfInputs, aUserInput, aScopeController);

        myEvaluationState = NodeState.EVALUATING_INPUTS;

        myChildConditions = new ArrayList<INode>();
        myChildBranches = new HashMap<Integer, List<INode>>();

        myNumberOfInputs = aNumberOfInputs;
        myCommand = (ICommandBranch) aCommand;
    }

    public int getNumberOfInputs () {
        return myNumberOfInputs;
    }

    public NodeState getEvaluationState () {
        return myEvaluationState;
    }

    public void setEvaluationState (NodeState aNodeState) {
        myEvaluationState = aNodeState;
    }

    @Override
    public double eval () throws ArgumentException {
        List<INode> currentBranch = myChildBranches.get(myActiveBranchIndex);
        INode[] inputList = currentBranch.toArray(new INode[currentBranch.size()]);
        myReturnValue = myCommand.eval(inputList);
        return myReturnValue;
    }

    public int evalCondition () {
        INode[] inputList = myChildConditions.toArray(new INode[myChildConditions.size()]);
        myConditionReturnValue = myCommand.evalCondition(inputList);
        if (myConditionReturnValue == -1) {
            myReturnValue = myConditionReturnValue;
        }
        // myEvaluationState = NodeState.EVALUATING_BRANCH;
        myActiveBranchIndex = myConditionReturnValue;
        return myConditionReturnValue;
    }

    public int getConditionEvaluation () {
        return myConditionReturnValue;
    }

    @Override
    public String getName () {
        return null;
    }

    @Override
    public double getValue () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<INode> getChildren () {
        switch (myEvaluationState) {
            case EVALUATING_INPUTS:
                return myChildConditions;
            case EVALUATING_BRANCH:
                return myChildBranches.get(myActiveBranchIndex);
            default:
                // XXX: Change to throw an error;
                return null;
        }
    }

    @Override
    public void addChild (INode aNode) {
        // XXX: remove this
        throw new RuntimeException("Do not use this method");
    }

    public void addConditions (INode aNode) {
        if (aNode instanceof ListNode) {
            addConditions((ListNode) aNode);
        }
        else {
            myChildConditions.add(aNode);
        }
    }

    private void addConditions (ListNode aList) {
        for (INode child : aList.getChildren()) {
            myChildConditions.add(child);
        }
    }

    public void addBranchChildren (int aBranchId, ListNode aListNode) {
        if (!myChildBranches.containsKey(aBranchId)) {
            myChildBranches.put(aBranchId, new ArrayList<INode>());
        }
        for (INode child : aListNode.getChildren()) {
            myChildBranches.get(aBranchId).add(child);
        }
    }

    public void unmarkAllChildren () {
        // Unmark all conditions
        for (INode child : myChildConditions) {
            child.setState(NodeState.AVAILABLE);
            unmarkAllChildren(child);
        }

        // Unmark all branches
        for (int branchId : myChildBranches.keySet()) {
            for (INode child : myChildBranches.get(branchId)) {
                child.setState(NodeState.AVAILABLE);
                unmarkAllChildren(child);
            }
        }
    }

    private void unmarkAllChildren (INode aParent) {
        if (aParent instanceof AbstractBranchNode) {
            ((AbstractBranchNode) aParent).unmarkAllChildren();
        }
        else {
            for (INode child : aParent.getChildren()) {
                child.setState(NodeState.AVAILABLE);
                unmarkAllChildren(child);
            }
        }
    }

}
