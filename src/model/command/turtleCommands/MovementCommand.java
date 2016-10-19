package model.command.turtleCommands;

import java.awt.Point;

import model.command.ICommand;
import model.exception.ArgumentException;
import model.robot.Robot;
import model.robot.Turtle;

public abstract class MovementCommand implements ICommand {
	
	private Robot myRobot;
	
	public MovementCommand(Robot aRobot) {
		myRobot = aRobot;
	}

	public Point getXYCoordinate(String... aList) throws ArgumentException {
		if (aList.length != 1) {
			throw new ArgumentException("Method Evaluation needs one argument");
		}
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
	
	public Robot getRobot() {
		return myRobot;
	}

}
