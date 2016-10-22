package model.command;

public class PowerCommand implements ICommand {
	
	public PowerCommand() {
	}

	@Override
	public double eval(String... aList) {
		return Math.pow(Double.parseDouble(aList[0]), Double.parseDouble(aList[1]));
	}

}