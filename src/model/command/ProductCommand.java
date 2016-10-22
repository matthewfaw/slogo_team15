package model.command;

public class ProductCommand implements ICommand {
	
	public ProductCommand() {
	}

	@Override
	public double eval(String... aList) {
		return Double.parseDouble(aList[0]) * Double.parseDouble(aList[1]);
	}

}