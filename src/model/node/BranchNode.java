package model.node;

import model.command.ICommand;
import model.states.Scope;

public class BranchNode implements Node, IReadableInput {

	public BranchNode(ICommand aCommand, int aNumberOfInputs, Scope aScope) {
		
	}
	
	@Override
	public double eval(IReadableInput... aList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
