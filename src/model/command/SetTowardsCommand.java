package model.command;

import model.exception.ArgumentException;
import model.robot.Robot;

public class SetTowardsCommand implements ICommand {
	private Robot myRobot;
	
	public SetTowardsCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		if (aList.length != 2) {
			throw new ArgumentException("Method Evaluation needs two argument");
		}
		double adj = Double.parseDouble(aList[0]) - myRobot.getX();
		double opp = Double.parseDouble(aList[1]) - myRobot.getY();
		double returnVal = 180 - Math.abs(myRobot.getRotation() - Math.atan(opp / adj));
		if (Double.parseDouble(aList[0]) > myRobot.getX()) {
			myRobot.setRotation(myRobot.getRotation() - returnVal);
		} else {
			myRobot.setRotation(myRobot.getRotation() + returnVal);
		}
		myRobot.notifyObservers();
		return returnVal;
	}
}
