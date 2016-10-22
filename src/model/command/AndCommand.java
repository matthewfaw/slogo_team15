package model.command;

public class AndCommand implements ICommand {
	
	public AndCommand() {
	}

	@Override
	public double eval(String... aList) {
		double returnVal = (Double.parseDouble(aList[0]) != 0 && Double.parseDouble(aList[1]) != 0) ? 1 : 0;
		return returnVal;
	}

}