package back_end.model.node;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.states.ScopeController;


public class NullNode extends Node {

    public NullNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
        super(aCommand, aNumberOfInputs, aUserInput, aScopeController);
    }

    @Override
    public String getName () {
        return null;
    }

    @Override
    public double getValue () {
        return 0;
    }

    @Override
    public double eval () throws ArgumentException {
        // TODO Auto-generated method stub
        return 0;
    }
}
