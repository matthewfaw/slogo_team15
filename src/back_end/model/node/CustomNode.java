package back_end.model.node;

import java.util.ArrayList;
import java.util.List;

import back_end.model.exception.ArgumentException;
import back_end.model.command.ICommandBranch;

public class CustomNode extends Node {
	private ICommandBranch myMethod;
	
	private ArrayList<Node> myInputs;
	private ArrayList<Node> myFunction;
	
	private int myReturnValue;
	private NodeState myEvaluationState;

	public CustomNode(ICommandBranch aCommand, int aNumberOfInputs)
	{
		super();
		
		myEvaluationState = NodeState.EVALUATING_INPUTS;
		
		myMethod = aCommand;
	}

	@Override
	public double eval() throws ArgumentException {
		Node[] inputs = myFunction.toArray(new Node[myFunction.size()]);
		myReturnValue = myMethod.eval(inputs);
		myMethod.resetScope();
		
		return myReturnValue;
	}
	public void evalInputs()
	{
		myMethod.setScope();

		Node[] myInputList = myInputs.toArray(new Node[myInputs.size()]);
		myMethod.eval(myInputList);
		
		myFunction = myMethod.getFunction().getChildren();
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public double getValue() {
		return myReturnValue;
	}
	
	public void addChildInputs(List<Node> aInputs)
	{
		myInputs = aInputs;
	}
	public List<Node> getChildInputs()
	{
		return myInputs;
	}
	
	public List<Node> getFunction()
	{
		return myFunction;
	}
	
	public void setEvaluationState(NodeState aState)
	{
		myEvaluationState = aState;
	}
	public NodeState getEvaluationState()
	{
		return myEvaluationState;
	}
	//XXX: combine with BranchNode since similar
	public void unmarkCustomChildren()
	{
		//Unmark all conditions
		for (Node child: myFunction) {
			child.setState(NodeState.AVAILABLE);
			unmarkCustomChildren(child);
		}
	}
	
	//XXX: combine with BranchNode since similar
	private void unmarkCustomChildren(Node aParent)
	{
		if (aParent instanceof CustomNode) {
			((CustomNode) aParent).unmarkCustomChildren();
		} else {
			for (Node child: aParent.getChildren()) {
				child.setState(NodeState.AVAILABLE);
				unmarkCustomChildren(child);
			}
		}
	}
	
}
