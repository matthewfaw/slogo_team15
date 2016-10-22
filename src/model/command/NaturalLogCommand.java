package model.command;


public class NaturalLogCommand implements ICommand {
	
	public NaturalLogCommand() {
	}

	@Override
	public double eval(String... aList) {
		return Math.log(Double.parseDouble(aList[0]));
	}

}