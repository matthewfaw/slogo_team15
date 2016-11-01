package back_end.model.node;

import back_end.model.exception.ArgumentException;
import back_end.model.exception.InvalidNodeUsageException;

public interface INode extends IReadableInput {
//    protected Node (ICommand aCommand, int aNumberOfInputs,  ScopeController aScopeController) {
//        myState = NodeState.AVAILABLE;
//    }
//
    public abstract NodeState getState () throws InvalidNodeUsageException; 
    public abstract boolean isVisited() throws InvalidNodeUsageException;
    public abstract void setState (NodeState aNodeState) throws InvalidNodeUsageException; 

    public abstract void eval () throws ArgumentException, InvalidNodeUsageException;

}
