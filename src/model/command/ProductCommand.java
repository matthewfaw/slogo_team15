package model.command;

import model.node.IReadableInput;

public class ProductCommand implements ICommand {
	
	public ProductCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		return aList[0].getValue() * aList[1].getValue();
	}

}