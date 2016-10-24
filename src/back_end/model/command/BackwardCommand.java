package back_end.model.command;

import java.awt.Point;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;

public class BackwardCommand extends MovementCommand {
	
	private Robot myRobot;
	
	public BackwardCommand(Robot aRobot) {
		super(aRobot);
		myRobot = aRobot;
	}

	@Override
	public double eval(IReadableInput... aList) {
		Point p = getXYCoordinate(aList);
		myRobot.setX(myRobot.getX() - p.getX());
		myRobot.setY(myRobot.getY() - p.getY());
		return aList[0].getValue();
	}

}