package model.command;

import java.util.Arrays;

import model.exception.ArgumentException;
import model.robot.Robot;

public class HomeCommand extends SetPositionCommand {

	public HomeCommand(Robot aRobot) {
		super(aRobot);
	}
	
	@Override
	public double eval(String... aList) throws ArgumentException {
		if (aList.length != 0) {
			throw new ArgumentException("Method Evaluation needs zero arguments");
		}
		String[] zeroList = new String[2]; 
		Arrays.fill(zeroList, "0");
		return super.eval(zeroList);
	}

}
