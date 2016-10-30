package back_end.model.node.leaf_nodes;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.states.ScopeController;


public class VariableNode implements ILeafNode {

    private String myName;
    private ScopeController myScopeController;

    public VariableNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
        myName = aUserInput;
        myScopeController = aScopeController;
    }

    @Override
    public double eval () throws ArgumentException {
    	return getValue();
    }

    @Override
    public String getName () {
        return myName;
    }

    @Override
    public double getValue () {
        return myScopeController.getVariableValue(myName);
    }
}
