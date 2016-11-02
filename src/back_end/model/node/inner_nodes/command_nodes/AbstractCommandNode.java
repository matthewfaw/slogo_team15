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


public abstract class AbstractCommandNode extends AbstractInnerNode {

    private int myNumberOfInputs;
    private ScopeController myScopeController;

    protected AbstractCommandNode(int aNumberOfInputs, ScopeController aScopeController) 
    {
        myNumberOfInputs = aNumberOfInputs;
        myScopeController = aScopeController;
    }
    
    protected abstract ICommand getCommand();
    /**
     * COMMENT THIS METHOD WELL
     * It's purpose is to allow multiple turtles to work!
     */
//    protected void register()
//    {
//    	if (getCommand() instanceof ICommandTurtle) {
//    		myScopeController.registerObserver(this);
//    	}
//    }
    protected ScopeController getScopeController()
    {
    	return myScopeController;
    }

    public int getNumberOfInputs () {
        return myNumberOfInputs;
    }
    
    protected INode[] convertListToProperInputForm(List<INode> aList) throws InvalidInputNumberException
    {
    	//XXX: Removing because we can't check for this here since we don't have enough info
    	// about how the methods are implemented -- ex: For loop evalCondition
		// if (aList.size() != myNumberOfInputs) {
		//     throw new InvalidInputNumberException("Invalid number of arguments");
		// }
        INode[] inputList = aList.toArray(new INode[aList.size()]);
        
        return inputList;
    }

    /**
     * a method that will reset the state so that this node can be evaluated again
     */
    protected abstract void resetStatesForNewTurtle() throws InvalidNodeUsageException;
}
