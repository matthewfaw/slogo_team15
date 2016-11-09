package back_end.model.node;

import back_end.model.exception.InvalidNodeUsageException;

/**
 * This interface provides a wrapper around the Node classes
 * So that we may pass Nodes to Command classes without exposing all of the
 * methods available to Node classes
 * 
 * @author matthewfaw and Hannah Fuchshuber 
 *
 */
public interface IReadableInput {

	/**
	 * This returns the name associated with a given node. 
	 * This method assumes that, when used, the node has
	 * a valid name associated with it
	 * 
	 * @return the name associated with the Node
	 * @throws InvalidNodeUsageException
	 */
    public String getName () throws InvalidNodeUsageException;

    /**
     * This method returns the return value associated with a node
     * This method throws an error when the node value is accessed before the node is marked as visited
     * @return the return value of the node
     * @throws InvalidNodeUsageException
     */
    public double getValue () throws InvalidNodeUsageException;

}
