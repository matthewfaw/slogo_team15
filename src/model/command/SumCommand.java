package model.command;


public class SumCommand implements ICommand {
	
	public SumCommand() {
	}

	@Override
	public double eval(String... aList) {
		return Double.parseDouble(aList[0]) + Double.parseDouble(aList[1]);
	}

}
