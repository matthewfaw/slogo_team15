package back_end.model.node.dummy_nodes;

import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;

/**
 * Abstract class to represent the functionality of nodes which do nothing
 * It is assumed that these classes will not be operated on, and as such,
 * calling these methods results in an error being thrown
 * 
 * @author matthewfaw
 *
 */
public abstract class AbstractDummyNode implements INode {
	//XXX: Pull this value from resource file, perhaps?
	private static final double DEFAULT_RETURN_VALUE = 0.0;

	protected AbstractDummyNode()
	{
	}

	@Override
    public NodeState getState () throws InvalidNodeUsageException 
    {
		throw new InvalidNodeUsageException("You shouldn't be accessing Dummy node states");
    }
	
	@Override
	public boolean isVisited() throws InvalidNodeUsageException
	{
		throw new InvalidNodeUsageException("You shouldn't be accessing Dummy node states");
	}

	@Override
    public void setState (NodeState aNodeState) throws InvalidNodeUsageException
    {
    	//XXX: Change this error message to be pulled from resource file
    	throw new InvalidNodeUsageException("Cannot set the state of a Dummy node");
    }
    	

	@Override
    public void eval () throws InvalidInputNumberException
    {
    }
    	
	@Override
	public String getName() throws InvalidNodeUsageException
	{
    	throw new InvalidNodeUsageException("Dummy nodes do not have names");
	}

	@Override
	public double getValue() 
	{
    	return DEFAULT_RETURN_VALUE;
	}

	public void resetStates() throws InvalidNodeUsageException
	{
		throw new InvalidNodeUsageException("You shouldn't be accessing dummy node states you dummy");
	}
}
