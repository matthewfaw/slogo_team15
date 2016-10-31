package back_end.model.node;

import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;

public interface INode extends IReadableInput {
//    protected Node (ICommand aCommand, int aNumberOfInputs,  ScopeController aScopeController) {
//        myState = NodeState.AVAILABLE;
//    }
//
    public abstract NodeState getState (); 
    public abstract void setState (NodeState aNodeState) throws InvalidNodeUsageException; 

    public abstract void eval () throws InvalidInputNumberException, InvalidNodeUsageException;

}
