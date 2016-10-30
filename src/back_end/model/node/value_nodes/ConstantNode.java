package back_end.model.node.value_nodes;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.states.ScopeController;


public class ConstantNode extends ValueNode {

    private double myValue;
    private int myNumberOfInputs;

    public ConstantNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
        super(aCommand, aNumberOfInputs, aUserInput, aScopeController);

        myValue = Double.parseDouble(aUserInput);
        myNumberOfInputs = 0;
    }

    @Override
    public double eval () throws ArgumentException {
        // TODO Auto-generated method stub
        return myValue;
    }

    public int getNumberOfInputs () {
        return myNumberOfInputs;
    }

    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getValue () {
        return myValue;
    }

}
