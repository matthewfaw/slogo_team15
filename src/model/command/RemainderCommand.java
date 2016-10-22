package model.command;

public class RemainderCommand implements ICommand {
	
	public RemainderCommand() {
	}

	@Override
	public double eval(String... aList) {
		return Double.parseDouble(aList[0]) % Double.parseDouble(aList[1]);
	}

}