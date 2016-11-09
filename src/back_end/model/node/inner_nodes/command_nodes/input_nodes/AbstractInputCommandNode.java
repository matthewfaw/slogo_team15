package back_end.model.node.inner_nodes.command_nodes.input_nodes;

import java.util.ArrayList;
import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.command.ICommandTurtle;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.EvaluationState;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.inner_nodes.command_nodes.AbstractCommandNode;
import back_end.model.node.inner_nodes.list_nodes.ListNode;
import back_end.model.states.ScopeController;

/**
 * A class that represents "normal" commands
 * It is assumed that each of these nodes will be evaluated only once
 * 
 * @author matthewfaw
 *
 */
public abstract class AbstractInputCommandNode extends AbstractCommandNode {

	private List<INode> myChildren;
	private ICommand myCommand;
	private double myValue;
	private EvaluationState myEvaluationState;

	public AbstractInputCommandNode(ICommand aCommand, int aNumberOfInputs, ScopeController aScopeController) 
	{
		super(aNumberOfInputs, aScopeController);
		
		myChildren = new ArrayList<INode>();
		myEvaluationState = EvaluationState.UNEVALUATED;
		myCommand = aCommand;
	}
	
	@Override
	protected ICommand getCommand()
	{
		return myCommand;
	}
	
	@Override
	public List<INode> getChildren() throws InvalidNodeUsageException
	{
		return myChildren;
	}
	/**
	 * Defines a default way to add a child to the list of children
	 * @param aChild
	 * @throws InvalidNodeUsageException
	 */
	protected void addChild(INode aChild) throws InvalidNodeUsageException
	{
		myChildren.add(aChild);
	}
	/**
	 * Defines a way to add a list of children to the node's children
	 * @param aChildren
	 */
	protected void addChildren(List<INode> aChildren)
	{
		myChildren.addAll(aChildren);
	}

	@Override
	public void eval() throws InvalidInputNumberException, InvalidNodeUsageException {
		INode[] inputList = super.convertListToProperInputForm(myChildren);

        myValue = myCommand.eval(inputList);
        myEvaluationState = EvaluationState.EVALUATED;
        super.setState(NodeState.VISITED);
        
        resetStatesForNewTurtle();
	}

	@Override
	public double getValue() throws InvalidNodeUsageException {
		if (myEvaluationState == EvaluationState.UNEVALUATED) {
			throw new InvalidNodeUsageException("This node has not been evaluated yet!");
		}
		return myValue;
	}

	@Override
    public void resetStatesForNewTurtle() throws InvalidNodeUsageException
    {
		if (myCommand instanceof ICommandTurtle) {
			getScopeController().setNextTurtleAsActive();
			if (!getScopeController().activeTurtleIndexHasBeenSetToStart()) {
				resetStates();
			}
		}
    }
	
	@Override
	public void resetStates() throws InvalidNodeUsageException
	{
		super.setState(NodeState.AVAILABLE);
		myEvaluationState = EvaluationState.UNEVALUATED;
	}
}
