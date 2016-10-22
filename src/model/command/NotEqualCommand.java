package model.command;

public class NotEqualCommand implements ICommand {
	
	public NotEqualCommand() {
	}

	@Override
	public double eval(String... aList) {
		double returnVal = (Double.parseDouble(aList[0]) != Double.parseDouble(aList[1])) ? 1 : 0;
		return returnVal;
	}

}