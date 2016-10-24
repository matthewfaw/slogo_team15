package back_end.model.node;

import java.util.List;

import back_end.model.exception.ArgumentException;

public class CustomNode extends Node {

	public CustomNode()
	{
		super();
	}

	@Override
	public double eval(List<Node> aList) throws ArgumentException {
		// TODO Auto-generated method stub
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
