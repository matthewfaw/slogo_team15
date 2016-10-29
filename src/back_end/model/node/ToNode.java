package back_end.model.node;

import java.util.ArrayList;
import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.states.Environment;

public class ToNode extends Node {
	private ICommand myMethod;
	private int myNumberOfInputs;
	
	private VariableNode myNameNode;
	private	List<Node> myInputVariables;
	private ListNode myCustomMethod;
	
	private double returnValue;
	
	public ToNode (ICommand aCommand, int aNumberOfInputs, String aUserInput) {
		myMethod = aCommand;
		myNumberOfInputs = aNumberOfInputs;
	}
	public int getNumberOfInputs()
	{
		return myNumberOfInputs;
	}
	public void addNameNode(VariableNode aNode)
	{
		myNameNode = aNode;
	}
	public void addInputVariables(ListNode aListNode)
	{
		myInputVariables = aListNode.getChildren();
	}
	public void addCustomMethod(ListNode aListNode)
	{
		myCustomMethod = aListNode;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getValue() {
		return returnValue;
	}

	@Override
	public double eval() throws ArgumentException {
		ArrayList<Node> allInputs = new ArrayList<Node>(myInputVariables);
		allInputs.add(0, myNameNode);
		allInputs.add(1, myCustomMethod);
		
		Node[] allInputsArray = allInputs.toArray(new Node[allInputs.size()]);

		returnValue = myMethod.eval(allInputsArray);
		
		return returnValue;
	}

}
