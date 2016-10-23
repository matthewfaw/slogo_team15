package model.node;

public class BeginBraceNode implements Node, IReadableInput {

	@Override
	public double eval(IReadableInput... aList) {
		return 0;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public double getValue() {
		return 0;
	}
	
}
