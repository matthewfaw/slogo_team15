package back_end.model.node.inner_nodes.command_nodes.input_nodes;

import java.util.ArrayList;
import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.EvaluationState;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.inner_nodes.command_nodes.AbstractCommandNode;
import back_end.model.node.inner_nodes.list_nodes.ListNode;
import back_end.model.states.ScopeController;

public abstract class AbstractInputCommandNode extends AbstractCommandNode {

	private List<INode> myChildren;
	private ICommand myCommand;
	private double myValue;
	private EvaluationState myEvaluationState;

	public AbstractInputCommandNode(ICommand aCommand, int aNumberOfInputs) 
	{
		super(aNumberOfInputs);
		
		myChildren = new ArrayList<INode>();
		myEvaluationState = EvaluationState.UNEVALUATED;
		myCommand = aCommand;
	}

	@Override
	public List<INode> getChildren() throws InvalidNodeUsageException
	{
		return myChildren;
	}
	protected void addChild(INode aChild) throws InvalidNodeUsageException
	{
		myChildren.add(aChild);
	}
	protected void addChildren(List<INode> aChildren)
	{
		myChildren.addAll(aChildren);
	}

	@Override
	public void eval() throws ArgumentException, InvalidNodeUsageException {
		INode[] inputList = super.convertListToProperInputForm(myChildren);

        myValue = myCommand.eval(inputList);
        myEvaluationState = EvaluationState.EVALUATED;
        super.setState(NodeState.VISITED);
	}

	@Override
	public double getValue() throws InvalidNodeUsageException {
		if (myEvaluationState == EvaluationState.UNEVALUATED) {
			throw new InvalidNodeUsageException("This node has not been evaluated yet!");
		}
		return myValue;
	}

}
