package back_end.model.node;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.states.ScopeController;


// XXX: Add stuff
public class ListNode extends Node {

    public ListNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
        super(aCommand, aNumberOfInputs, aUserInput, aScopeController);
    }

    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getValue () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double eval () throws ArgumentException {
        // TODO Auto-generated method stub
        return 0;
    }
}
