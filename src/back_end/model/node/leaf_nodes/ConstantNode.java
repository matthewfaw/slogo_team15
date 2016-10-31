package back_end.model.node.leaf_nodes;

import back_end.model.command.ICommand;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.states.ScopeController;


public class ConstantNode extends AbstractLeafNode {

    private double myValue;

    public ConstantNode (ICommand aCommand, int aNumberOfInputs, String aUserInput, ScopeController aScopeController) {
    	super();

        myValue = Double.parseDouble(aUserInput);
    }

	@Override
	public void eval() throws InvalidInputNumberException {
		// do nothing
	}

	@Override
	public String getName() throws InvalidNodeUsageException {
		throw new InvalidNodeUsageException("Constant nodes do not have a name");
	}

	@Override
	public double getValue() {
		return myValue;
	}

}
