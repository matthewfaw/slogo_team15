package back_end.model.robot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import integration.drawing.PenInformation;
import integration.observe.Observable;

public class RobotController extends Observable implements IRobot {
	
	private List<Turtle> myTurtles;
	private int currentTurtle;
	private List<Turtle> myActiveTurtles;
	
	public RobotController() {
		myTurtles = new ArrayList<Turtle>();
		myActiveTurtles = new ArrayList<Turtle>();
		currentTurtle = 1;
		addTurtle(currentTurtle);
	}
	
	@Override
	public void setActiveTurtles(int[] aTurtleIDs) {
		for (int i = 0; i < aTurtleIDs.length; i++) {
			if (aTurtleIDs[i] < getNumberOfTurtles()) {
				addTurtle(aTurtleIDs.length);
			}
		}
		for (int i = 0; i < aTurtleIDs.length; i++) {
			myActiveTurtles.add(myTurtles.get(aTurtleIDs[i]));
		}
		currentTurtle = aTurtleIDs[aTurtleIDs.length - 1];
	}
	
	private void addTurtle(int aTurtleID) {
		for (int i = myTurtles.size() - 1; i < aTurtleID; i++) {
			Turtle turtle = new Turtle();
			myTurtles.add(turtle);
		}
	}
	
	/**GETTERS**/
	
	@Override
	public Turtle getTurtle(int aTurtleID) {
		return myTurtles.get(aTurtleID);
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

	/**SETTERS**/ 
	
	@Override
	public void setX(double x) {
		for (int i = 0; i < myActiveTurtles.size(); i++) {
			myActiveTurtles.get(i).setX(x);
		}
	}

	@Override
	public void setY(double y) {
		for (int i = 0; i < myActiveTurtles.size(); i++) {
			myActiveTurtles.get(i).setY(y);
		}
	}

	@Override
	public void setRotation(double r) {
		for (int i = 0; i < myActiveTurtles.size(); i++) {
			myActiveTurtles.get(i).setRotation(r);
		}
	}

	@Override
	public void setVisible(boolean t) {
		for (int i = 0; i < myActiveTurtles.size(); i++) {
			myActiveTurtles.get(i).setVisible(t);
		}
	}

	@Override
	public void setPenInformation(PenInformation aPenInformation) {
		for (int i = 0; i < myActiveTurtles.size(); i++) {
			myActiveTurtles.get(i).setPenInformation(aPenInformation);
		}
	}

	@Override
	public void setImageID(int aImageID) {
		for (int i = 0; i < myActiveTurtles.size(); i++) {
			myActiveTurtles.get(i).setImageID(aImageID);
		}
	}

	@Override
	public void setTurtleID(int aTurtleID) {
		for (int i = 0; i < myActiveTurtles.size(); i++) {
			myActiveTurtles.get(i).setTurtleID(aTurtleID);
		}
	}

}
