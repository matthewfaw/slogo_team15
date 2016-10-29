package back_end.model.node;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.states.ScopeController;


public class VariableNode extends ValueNode {

    private String myName;
    private int myNumberOfInputs = 0;
    private ScopeController myScopeController;
    // private double myValue;

    public VariableNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
        super(aCommand, aNumberOfInputs, aUserInput, aScopeController);

        myName = aUserInput;
        myScopeController = aScopeController;
    }

    @Override
    public double eval () throws ArgumentException {
        //return myScopeController.getVariableValue(myName);
    	return 0;
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
        //return myScopeController.getVariableValue(myName);
    	return 0;
    }
}
