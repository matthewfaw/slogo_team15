package model.command;

public class RandomCommand implements ICommand {
	
	public RandomCommand() {
	}

	@Override
	public double eval(String... aList) {
		return Math.random() * Double.parseDouble(aList[0]);
	}

}