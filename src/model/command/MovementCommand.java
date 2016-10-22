package model.command;

import java.awt.Point;

import model.robot.Robot;

public abstract class MovementCommand implements ICommand {
	
	private Robot myRobot;
	
	public MovementCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	public Point getXYCoordinate(String... aList) {
		double XPos = Math.sin(myRobot.getRotation()) * Double.parseDouble(aList[0]);
		double YPos = Math.cos(myRobot.getRotation()) * Double.parseDouble(aList[0]);
		if (myRobot.getRotation() > 90 && myRobot.getRotation() < 360) {
			XPos = 0 - XPos;
			YPos = 0 - YPos;
		}
		Point p = new Point();
		p.setLocation(XPos, YPos);
		return p;	
	}


}
