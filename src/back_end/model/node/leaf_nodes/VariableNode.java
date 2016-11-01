package back_end.model.node.leaf_nodes;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.NodeState;
import back_end.model.states.ScopeController;


public class VariableNode extends AbstractLeafNode {

    private String myName;
    private ICommand myCommand;

    public VariableNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) 
    {
    	super();
    	
        myName = aUserInput;
        myCommand = aCommand;
    }

	@Override
	public void eval() throws ArgumentException, InvalidNodeUsageException 
	{
		super.setState(NodeState.VISITED);
	}

	@Override
	public String getName() throws InvalidNodeUsageException 
	{
		return myName;
	}

	/**
	 * Since we wanted to enforce a divide between who has access
	 * to environment variables and who has access to scope, 
	 * get value calls the command to get the value associated with 
	 * this node's name
	 * @throws InvalidNodeUsageException 
	 */
	@Override
	public double getValue() throws InvalidNodeUsageException 
	{
		return myCommand.eval(this);
	}
}
