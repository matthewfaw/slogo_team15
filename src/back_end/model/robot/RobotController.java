package back_end.model.robot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;

import integration.drawing.PenInformation;
import integration.observe.Observable;

public class RobotController extends Observable implements IRobot {
	private static final int INITIAL_TURTLE_INDEX = 1;
	
	private HashMap<Integer, Turtle> myTurtles;
	private Turtle myCurrentlyActiveTurtle;
	private int myCurrentlyActiveTurtleIndex;
	private HashMap<Integer, Turtle> myActiveTurtles;
	private Turtle myMostRecentlyCreatedTurtle;
	
	public RobotController() {
		System.out.println("derp");
		myTurtles = new HashMap<Integer, Turtle>();
		myActiveTurtles = new HashMap<Integer, Turtle>();
		addTurtle(INITIAL_TURTLE_INDEX);
		addActiveTurtle(INITIAL_TURTLE_INDEX);
		setTurtleAsCurrentlyActive(INITIAL_TURTLE_INDEX);
	}
	
	public void addActiveTurtle(int aTotalTurtleListIndex)
	{
		Turtle turtle = myTurtles.get(aTotalTurtleListIndex);
		
		if (!myActiveTurtles.containsKey(aTotalTurtleListIndex)) {
			myActiveTurtles.put(aTotalTurtleListIndex,turtle);
		}
	}
	
	public void clearActiveTurtles() {
		myCurrentlyActiveTurtleIndex = -1;
		myCurrentlyActiveTurtle = null;
		myActiveTurtles.clear();
	}
	
	public void setTurtleAsCurrentlyActive(int aActiveTurtleIndex)
	{
		myCurrentlyActiveTurtleIndex = aActiveTurtleIndex;
		myCurrentlyActiveTurtle = myActiveTurtles.get(aActiveTurtleIndex);
	}
	
	public Collection<Turtle> getCurrentlyActiveTurtles() {
		return myActiveTurtles.values();
	}
	
	public Turtle getCurrentTurtle() {
		return myCurrentlyActiveTurtle;
	}
	
	public void setNextTurtleAsActive()
	{
		Integer[] indices = myActiveTurtles.keySet().toArray(new Integer[myActiveTurtles.keySet().size()]);
		int newIndex = findNextIndexInArray(indices, myCurrentlyActiveTurtleIndex);
		
		setTurtleAsCurrentlyActive(newIndex);
	}
	
	private int findNextIndexInArray(Integer[] indices, int indexToFind)
	{
		for (int i=0; i<indices.length; ++i) {
			if (indices[i] == indexToFind) {
				return indices[(i+1) % indices.length];
			}
		}
		return -2;
	}
	
	public int numberOfTurtlesCreated() {
		return myTurtles.keySet().size();
	}
	
	public boolean activeTurtleIndexHasBeenSetToStart()
	{
		ArrayList<Integer> indices = new ArrayList<Integer>(myActiveTurtles.keySet());
		int firstIndex = indices.get(0);
		return myCurrentlyActiveTurtleIndex == firstIndex;
//		return myCurrentlyActiveTurtleIndex == INITIAL_TURTLE_INDEX;
	}
	
	public boolean containsTurtles(int aIndex) {
		return myTurtles.containsKey(aIndex);
	}
	
	
	public IViewableRobot getMostRecentRobot(){
		return myMostRecentlyCreatedTurtle;
	}
	
	public void addTurtle(int aIndex)
	{
		Turtle turtle = new Turtle(aIndex);
		myMostRecentlyCreatedTurtle = turtle;
		myTurtles.put(aIndex, turtle);
		notifyObservers();
	}
	
	/**GETTERS**/
	
	@Override
	public Turtle getTurtle(int aTurtleID) {
		return myTurtles.get(aTurtleID);
	}
	
	
	@Override
	public int getNumberOfTurtles() {
		return myTurtles.size();
	}
	

	@Override
	public Point getCurrentCoordinate() {
		return myCurrentlyActiveTurtle.getCurrentCoordinate();
	}

	@Override
	public Point getPreviousCoordinate() {
		return myCurrentlyActiveTurtle.getPreviousCoordinate();
	}


	@Override
	public int getImageID() {
		return myCurrentlyActiveTurtle.getImageID();
	}

	@Override
	public PenInformation getPenInformation() {
		return myCurrentlyActiveTurtle.getPenInformation();
	}

	@Override
	public int getTurtleID() {
		return myCurrentlyActiveTurtle.getTurtleID();
	}

	@Override
	public double getCurrentRotation() {
		return myCurrentlyActiveTurtle.getCurrentRotation();
	}

	@Override
	public boolean isVisible() {
		return myCurrentlyActiveTurtle.isVisible();
	}

	/**SETTERS**/ 
	
	@Override
	public void setCoordinates(double x, double y) {
		myCurrentlyActiveTurtle.setCoordinates(x,y);
	}

	@Override
	public void setRotation(double r) {
		myCurrentlyActiveTurtle.setRotation(r);
	}

	@Override
	public void setVisible(boolean t) {
		myCurrentlyActiveTurtle.setVisible(t);
	}

	@Override
	public void setPenInformation(PenInformation aPenInformation) {
		myCurrentlyActiveTurtle.setPenInformation(aPenInformation);
	}

	@Override
	public void setImageID(int aImageID) {
		myCurrentlyActiveTurtle.setImageID(aImageID);
	}

	@Override
	public double getPreviousRotation() {
		return myCurrentlyActiveTurtle.getPreviousRotation();
	}


}
