package back_end.model.node;

import java.util.ArrayList;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.states.Scope;

public class ToNode extends Node {
	private ICommand myMethod;
	private int myNumberOfInputs;
	
	private VariableNode myNameNode;
	private	ArrayList<VariableNode> myInputVariables;
	private ListNode myCustomMethod;
	
	private double returnValue;
	
	public ToNode(ICommand aMethod, int aNumberOfInputs, Scope aScope)
	{
		myMethod = aMethod;
		myNumberOfInputs = aNumberOfInputs;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
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
