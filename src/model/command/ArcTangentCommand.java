package model.command;

public class ArcTangentCommand implements ICommand {
	
	public ArcTangentCommand() {
	}

	@Override
	public double eval(String... aList) {
		return Math.atan(Double.parseDouble(aList[0]));
	}

}