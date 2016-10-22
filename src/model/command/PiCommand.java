package model.command;

public class PiCommand implements ICommand {
	
	public PiCommand() {
	}

	@Override
	public double eval(String... aList) {
		return Math.PI;
	}

}