package model.command;

public class NotCommand implements ICommand {
	
	public NotCommand() {
	}

	@Override
	public double eval(String... aList) {
		double returnVal = (Double.parseDouble(aList[0]) == 0) ? 1 : 0;
		return returnVal;
	}

}