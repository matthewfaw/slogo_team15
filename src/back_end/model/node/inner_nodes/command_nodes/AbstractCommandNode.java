package back_end.model.node.inner_nodes.command_nodes;

import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.INode;
import back_end.model.node.NodeState;
import back_end.model.node.inner_nodes.AbstractInnerNode;
import back_end.model.states.ScopeController;
import integration.observe.IObserver;

/**
 * A class that represents the default behavior of nodes which contain commands
 * It is assumed that these nodes all have commands.
 * 
 * This class depends on the scope controller to control entering and exiting scope 
 * @author matthewfaw
 *
 */
public abstract class AbstractCommandNode extends AbstractInnerNode {

    private int myNumberOfInputs;
    private ScopeController myScopeController;

    protected AbstractCommandNode(int aNumberOfInputs, ScopeController aScopeController) 
    {
        myNumberOfInputs = aNumberOfInputs;
        myScopeController = aScopeController;
    }
    
    /**
     * Assumes that the superclass is constructed with a Command object
     * @return the command owned by this class
     */
    protected abstract ICommand getCommand();
    /**
     * 
     * @return the scope controller object owned by this object
     */
    protected ScopeController getScopeController()
    {
    	return myScopeController;
    }

    /**
     * Returns the number of function inputs corresponding to this node's command object
     * @return
     */
    public int getNumberOfInputs () {
        return myNumberOfInputs;
    }
    
    protected INode[] convertListToProperInputForm(List<INode> aList) throws InvalidInputNumberException
    {
        INode[] inputList = aList.toArray(new INode[aList.size()]);
        
        return inputList;
    }

    /**
     * a method that will reset the state so that this node can be evaluated again
     */
    protected abstract void resetStatesForNewTurtle() throws InvalidNodeUsageException;
}
