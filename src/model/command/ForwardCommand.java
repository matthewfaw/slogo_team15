package model.command;

import java.awt.Point;

import model.exception.ArgumentException;
import model.robot.Turtle;

public class ForwardCommand extends MovementCommand {
	
	public ForwardCommand(Turtle aRobot) {
		super(aRobot);
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		Point p = getXYCoordinate(aList);
		getRobot().setX(getRobot().getX() + p.getX());
		getRobot().setY(getRobot().getY() + p.getY());
		return Double.parseDouble(aList[0]);
	}

}
