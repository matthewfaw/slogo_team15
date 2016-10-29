package back_end.model.node;

import back_end.model.command.ICommand;
import back_end.model.exception.ArgumentException;
import back_end.model.states.Environment;


public class VariableNode extends ValueNode {

    private String myName;
    private int myNumberOfInputs = 0;
    private Environment myEnvironment;
    // private double myValue;

    public VariableNode (ICommand aCommand, int aNumberOfInputs, String aUserInput) {
        super(aCommand, aNumberOfInputs, aUserInput);

        myName = aUserInput;
        myEnvironment = aEnvironment;
    }

    @Override
    public double eval () throws ArgumentException {
        return myEnvironment.getVariableValue(myName);
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
        return myEnvironment.getVariableValue(myName);
    }
}
