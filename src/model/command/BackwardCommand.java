package model.command;

import java.awt.Point;

import model.robot.Robot;

public class BackwardCommand extends MovementCommand {
	
	public BackwardCommand(Robot aRobot) {
		super(aRobot);
	}

	@Override
	public double eval(String... aList) {
		Point p = getXYCoordinate(aList);
		getRobot().setX(getRobot().getX() - p.getX());
		getRobot().setY(getRobot().getY() - p.getY());
		return Double.parseDouble(aList[0]);
	}

}