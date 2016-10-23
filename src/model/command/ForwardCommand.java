package model.command;

import java.awt.Point;

import model.node.IReadableInput;
import model.robot.Robot;

public class ForwardCommand extends MovementCommand {
	
	private Robot myRobot;
	
	public ForwardCommand(Robot aRobot) {
		super(aRobot);
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		Point p = getXYCoordinate(aList);
		myRobot.setX(myRobot.getX() + p.getX());
		myRobot.setY(myRobot.getY() + p.getY());
		return aList[0].getValue();
	}

}
