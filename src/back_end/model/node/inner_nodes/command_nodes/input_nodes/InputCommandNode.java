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
import back_end.model.states.ScopeController;

public class InputCommandNode extends AbstractCommandNode {

	private List<INode> myChildren;
	private ICommand myCommand;
	private double myValue;
	private EvaluationState myEvaluationState;

	public InputCommandNode(ICommand aCommand, int aNumberOfInputs, String aUserInput,
			ScopeController aScopeController) {
		super(aNumberOfInputs);
		
		myChildren = new ArrayList<INode>();
		myEvaluationState = EvaluationState.UNEVALUATED;
	}

	@Override
	public List<INode> getChildren() throws InvalidNodeUsageException
	{
		return myChildren;
	}

	@Override
	public void setChildren(List<INode> aChildren) {
		myChildren = aChildren;
	}

	@Override
	public void eval() throws ArgumentException, InvalidNodeUsageException {
		INode[] inputList = super.convertListToProperInputForm(myChildren);

        myValue = myCommand.eval(inputList);
        myEvaluationState = EvaluationState.EVALUATED;
	}

	@Override
	public double getValue() throws InvalidNodeUsageException {
		if (myEvaluationState == EvaluationState.UNEVALUATED) {
			throw new InvalidNodeUsageException("This node has not been evaluated yet!");
		}
		return myValue;
	}

}
