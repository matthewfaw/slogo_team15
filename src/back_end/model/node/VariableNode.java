package back_end.model.node;

import back_end.model.exception.ArgumentException;
import back_end.model.states.Scope;


public class VariableNode extends ValueNode {

    private String myName;
    private int myNumberOfInputs = 0;
    private Scope myScope;
    // private double myValue;

    public VariableNode (String aVariable, Scope aScope) {
        super();

        myName = aVariable;
        myScope = aScope;
    }

    @Override
    public double eval () throws ArgumentException {
        return myScope.getVariableValue(myName);
    }

    public int getNumberOfInputs () {
        return myNumberOfInputs;
    }

    @Override
    public String getName () {
        return myName;
    }

    @Override
    public double getValue () {
        return myScope.getVariableValue(myName);
    }
}
