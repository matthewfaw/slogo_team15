package model.command;

public class MinusCommand implements ICommand {
	
	public MinusCommand() {
	}

	@Override
	public double eval(String... aList) {
		return 0 - Double.parseDouble(aList[0]);
	}

}