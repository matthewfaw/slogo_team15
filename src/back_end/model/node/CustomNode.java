package back_end.model.node;

import java.util.List;

import back_end.model.exception.ArgumentException;
import back_end.model.states.Scope;
import back_end.model.command.CustomCommand;

public class CustomNode extends Node {
	private CustomCommand myMethod;
	
	private int myNumberOfInputs;
	
	private List<Node> myInputs;
	private List<Node> myFunction;
	
	private double myReturnValue;
	private NodeState myEvaluationState;

	public CustomNode(CustomCommand aCommand, int aNumberOfInputs, Scope aScope)
	{
		super();
		
		myNumberOfInputs = aNumberOfInputs;
		
		myEvaluationState = NodeState.EVALUATING_INPUTS;
		
		myMethod = aCommand;
	}
	
	public int getNumberOfInputs()
	{
		return myNumberOfInputs;
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
		
		myFunction = ((Node) myMethod.getFunction()).getChildren();
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public double getValue() {
		return myReturnValue;
	}
	
	public void addChildInputs(ListNode aInputs)
	{
		myInputs = aInputs.getChildren();
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
