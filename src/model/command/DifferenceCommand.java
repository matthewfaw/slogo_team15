package model.command;


public class DifferenceCommand implements ICommand {
	
	public DifferenceCommand() {
	}

	@Override
	public double eval(String... aList) {
		return Double.parseDouble(aList[0]) - Double.parseDouble(aList[1]);
	}

}