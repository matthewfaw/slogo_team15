package back_end.model.node;

import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;

/**
 * The purpose of this interface is to describe the basic functionality that every node
 * in the tree should have
 * 
 * Given any node, we may call:
 * node.getState(); to determine it's current NodeState,
 * node.isVisited(); to determine if the node has been visited
 * node.setState(NodeState.Available); to change the node's state
 * node.eval(); To evaluate the current node, and
 * node.resetStates(); to reset all stored states in a node to their default values
 * 
 * @author matthewfaw
 *
 */
public interface INode extends IReadableInput {

	/**
	 * A method to get the current NodeState of a given node
	 * Throws an error if Node State cannot be accessed
	 * @return the current NodeState of the node
	 * @throws InvalidNodeUsageException
	 */
    public abstract NodeState getState () throws InvalidNodeUsageException; 
    /**
     * a method to determine if a node has been visited 
     * @return true if the node has been evaluated, false otherwise
     * @throws InvalidNodeUsageException
     */
    public abstract boolean isVisited() throws InvalidNodeUsageException;
    /**
     * a method to change the state of a given node
     * @param aNodeState the NodeState to change to
     * @throws InvalidNodeUsageException
     */
    public abstract void setState (NodeState aNodeState) throws InvalidNodeUsageException; 

    /**
     * A method used to evaluate a node, given the return values of its children and the current
     * state of the node
     * This method will return an error if the method is called with the incorrect number of inputs,
     * or if an invalid node is accessed 
     * @throws InvalidInputNumberException
     * @throws InvalidNodeUsageException
     */
    public abstract void eval () throws InvalidInputNumberException, InvalidNodeUsageException;

    /**
     * This method is used to reset all states associated with a given node
     * This can be the NodeState, or the NodeState and EvaluationState.
     * This method assumes that all node states associated with a given node will be reset by this
     * method
     * @throws InvalidNodeUsageException
     */
	public abstract void resetStates() throws InvalidNodeUsageException;
}
