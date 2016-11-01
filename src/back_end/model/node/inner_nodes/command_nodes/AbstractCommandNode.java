package back_end.model.node.inner_nodes.command_nodes;

import java.util.List;

import back_end.model.command.ICommand;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.node.INode;
import back_end.model.node.inner_nodes.AbstractInnerNode;
import back_end.model.states.ScopeController;


public abstract class AbstractCommandNode extends AbstractInnerNode {

    private int myNumberOfInputs;

    protected AbstractCommandNode(int aNumberOfInputs) 
    {
        myNumberOfInputs = aNumberOfInputs;
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

}
