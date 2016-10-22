package model.command;


public class CosineCommand implements ICommand {
	
	public CosineCommand() {
	}

	@Override
	public double eval(String... aList) {
		return Math.cos(Double.parseDouble(aList[0]));
	}

}