package model.command;

public class TangentCommand implements ICommand {
	
	public TangentCommand() {
	}

	@Override
	public double eval(String... aList) {
		return Math.tan(Double.parseDouble(aList[0]));
	}

}