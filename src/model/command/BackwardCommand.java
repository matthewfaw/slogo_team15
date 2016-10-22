package model.command;

import java.awt.Point;

import model.robot.Robot;

public class BackwardCommand extends MovementCommand {
	
	private Robot myRobot;
	
	public BackwardCommand(Robot aRobot) {
		super(aRobot);
		myRobot = aRobot;
	}

	@Override
	public double eval(String... aList) {
		Point p = getXYCoordinate(aList);
		myRobot.setX(myRobot.getX() - p.getX());
		myRobot.setY(myRobot.getY() - p.getY());
		return Double.parseDouble(aList[0]);
	}

}