package model.command;

import model.robot.Robot;

public class SetPositionCommand implements ICommand {
	
	private Robot myRobot;
	
	public SetPositionCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) {
		double posX = Math.abs(myRobot.getX() - Double.parseDouble(aList[0]));
		double posY = Math.abs(myRobot.getY() - Double.parseDouble(aList[1]));
		double returnVal = Math.sqrt(Math.pow(posX, 2) + Math.pow(posY, 2));
		myRobot.setX(Double.parseDouble(aList[0]));
		myRobot.setY(Double.parseDouble(aList[1]));
		myRobot.notifyObservers();
		return returnVal;
	}
	
	

}
