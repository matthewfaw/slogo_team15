package back_end.model.robot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import integration.drawing.PenInformation;
import integration.observe.Observable;

public class RobotController extends Observable implements IRobot {
	
	private List<Turtle> myTurtles;
	private int currentTurtle;
	
	public RobotController() {
		myTurtles = new ArrayList<Turtle>();
		currentTurtle = 1;
		addTurtle(currentTurtle);
	}
	
	@Override
	public void addTurtle(int aTurtleID) {
		for (int i = myTurtles.size() - 1; i < aTurtleID; i++) {
			Turtle turtle = new Turtle();
			myTurtles.add(turtle);
		}
		currentTurtle = aTurtleID;
	}
	
	@Override
	public int getCurrentID() {
		return currentTurtle;
	}
	
	@Override
	public int getNumberOfTurtles() {
		return myTurtles.size() - 1;
	}
	

	@Override
	public Point getCoordinate() {
		return myTurtles.get(currentTurtle - 1).getCoordinate();
	}

	@Override
	public int getImageID() {
		return myTurtles.get(currentTurtle - 1).getImageID();
	}

	@Override
	public PenInformation getPenInformation() {
		return myTurtles.get(currentTurtle - 1).getPenInformation();
	}

	@Override
	public int getTurtleID() {
		return currentTurtle;
	}

	@Override
	public double getRotation() {
		return myTurtles.get(currentTurtle - 1).getRotation();
	}

	@Override
	public boolean isVisible() {
		return myTurtles.get(currentTurtle - 1).isVisible();
	}

	@Override
	public void setX(double x) {
		 myTurtles.get(currentTurtle - 1).setX(x);
	}

	@Override
	public void setY(double y) {
		 myTurtles.get(currentTurtle - 1).setY(y);
	}

	@Override
	public void setRotation(double r) {
		 myTurtles.get(currentTurtle - 1).setRotation(r);
	}

	@Override
	public void setVisible(boolean t) {
		 myTurtles.get(currentTurtle - 1).setVisible(t);
	}

	@Override
	public void setPenInformation(PenInformation aPenInformation) {
		 myTurtles.get(currentTurtle - 1).setPenInformation(aPenInformation);
	}

	@Override
	public void setImageID(int aImageID) {
		myTurtles.get(currentTurtle - 1).setImageID(aImageID);
	}

	@Override
	public void setTurtleID(int aTurtleID) {
		 myTurtles.get(currentTurtle - 1).setTurtleID(aTurtleID);	
	}

}
