package back_end.model.node.inner_nodes.command_nodes.branching_nodes;

import java.util.ArrayList;
import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.node.INode;
import back_end.model.node.inner_nodes.list_nodes.ListNode;
import back_end.model.node.leaf_nodes.VariableNode;
import back_end.model.states.Environment;
import back_end.model.states.ScopeController;

public class ToNode extends AbstractBranchNode {
	private ICommand myMethod;
	private int myNumberOfInputs;
	
	private VariableNode myNameNode;
	private	List<INode> myInputVariables;
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
		ArrayList<INode> allInputs = new ArrayList<INode>(myInputVariables);
		allInputs.add(0, myNameNode);
		allInputs.add(1, myCustomMethod);
		
		INode[] allInputsArray = allInputs.toArray(new INode[allInputs.size()]);

		returnValue = myMethod.eval(allInputsArray);
		
		return returnValue;
	}

}
