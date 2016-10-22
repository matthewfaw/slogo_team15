package model.command;


public class SineCommand implements ICommand {
	
	public SineCommand() {
	}

	@Override
	public double eval(String... aList) {
		return Math.sin(Double.parseDouble(aList[0]));
	}

}