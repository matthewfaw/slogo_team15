package model.node;

import model.exception.ArgumentException;

public class NullNode implements INode, IReadableInput {

	@Override
	public String getName() {
		return null;
	}

	@Override
	public double getValue() {
		return 0;
	}

	@Override
	public double eval(IReadableInput... aList) throws ArgumentException {
		return 0;
	}

}
