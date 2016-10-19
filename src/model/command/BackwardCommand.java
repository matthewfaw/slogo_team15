package model.command;

import java.awt.Point;

import model.exception.ArgumentException;
import model.robot.Turtle;

public class BackwardCommand extends MovementCommand {
	
	public BackwardCommand(Turtle aRobot) {
		super(aRobot);
	}

	@Override
	public void eval(String... aList) throws ArgumentException {
		Point p = getXYCoordinate(aList);
		getRobot().setX(getRobot().getX() - p.getX());
		getRobot().setY(getRobot().getY() - p.getY());
	}

}