package back_end.model.node.dummy_nodes;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.node.Node;
import back_end.model.states.ScopeController;


public class ListEndNode extends Node {

    public ListEndNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
        super(aCommand, aNumberOfInputs, aUserInput, aScopeController);
    }

    @Override
    public double eval () throws ArgumentException {
        // TODO Auto-generated method stub
        return 0;
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

}
