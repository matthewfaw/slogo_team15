package model.command;

public class QuotientCommand implements ICommand {
	
	public QuotientCommand() {
	}

	@Override
	public double eval(String... aList) {
		return Double.parseDouble(aList[0]) / Double.parseDouble(aList[1]);
	}

}