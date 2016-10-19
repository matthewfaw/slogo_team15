package model.command.turtleCommands;

import java.awt.Point;

import model.exception.ArgumentException;
import model.robot.Robot;

public class ForwardCommand extends MovementCommand {
	
	public ForwardCommand(Robot aRobot) {
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
