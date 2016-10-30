package back_end.model.node;

import back_end.model.exception.ArgumentException;

public interface INode extends IReadableInput {
//    protected Node (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
//        myState = NodeState.AVAILABLE;
//    }
//
    public abstract NodeState getState (); 
    public abstract void setState (NodeState aNodeState); 

    public abstract double eval () throws ArgumentException;

}
