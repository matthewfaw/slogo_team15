package back_end.model.node.inner_nodes.list_nodes;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.node.INode;
import back_end.model.node.inner_nodes.IInnerNode;
import back_end.model.states.ScopeController;


// XXX: Add stuff
public class ListNode implements IInnerNode {

    public ListNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
    }
    public ListNode()
    {
    	this(null,0,null,null);
    }

}
