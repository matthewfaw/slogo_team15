package back_end.model.node.command_nodes;

import java.util.ArrayList;
import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.node.Node;
import back_end.model.node.grouping_nodes.ListNode;
import back_end.model.node.value_nodes.VariableNode;
import back_end.model.states.Environment;
import back_end.model.states.ScopeController;

public class ToNode extends Node {
	private ICommand myMethod;
	private int myNumberOfInputs;
	
	private VariableNode myNameNode;
	private	List<Node> myInputVariables;
	private ListNode myCustomMethod;
	
	private double returnValue;
	
	public ToNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
		super(aCommand, aNumberOfInputs, aUserInput, aScopeController);
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
