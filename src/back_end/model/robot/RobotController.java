package back_end.model.robot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Collection;

import integration.drawing.PenInformation;
import integration.observe.AbstractObservable;

public class RobotController extends AbstractObservable implements IRobot {
	private static final int INITIAL_TURTLE_INDEX = 1;
	
	private HashMap<Integer, Turtle> myTurtles;
	private Turtle myCurrentlyActiveTurtle;
	private int myCurrentlyActiveTurtleIndex;
	private HashMap<Integer, Turtle> myActiveTurtles;
	private Turtle myMostRecentlyCreatedTurtle;
	
	private Stack<HashMap<Integer, Turtle>> myActiveTurtleStack;
	private Stack<Turtle> myCurrentActiveTurtleStack;
	
	public RobotController() {
		myActiveTurtleStack = new Stack<HashMap<Integer, Turtle>>();
		myActiveTurtleStack.push(new HashMap<Integer, Turtle>());
		myCurrentActiveTurtleStack = new Stack<Turtle>();

		myTurtles = new HashMap<Integer, Turtle>();
		myActiveTurtles = myActiveTurtleStack.peek();
		addTurtle(INITIAL_TURTLE_INDEX);
		addActiveTurtle(INITIAL_TURTLE_INDEX);
		setTurtleAsCurrentlyActive(INITIAL_TURTLE_INDEX);
	}
	
	/**
	 * Adds an active Turtle to the list 
	 * @param aTotalTurtleListIndex
	 */
	public void addActiveTurtle(int aTotalTurtleListIndex)
	{
		Turtle turtle = myTurtles.get(aTotalTurtleListIndex);
		
		if (!myActiveTurtles.containsKey(aTotalTurtleListIndex)) {
			myActiveTurtles.put(aTotalTurtleListIndex,turtle);
		}
	}
	
	/**
	 * Clears the list of active turtles
	 */
	public void clearActiveTurtles() {
		myCurrentlyActiveTurtleIndex = -1;
		myCurrentlyActiveTurtle = null;
		myActiveTurtles.clear();
	}
	
	/**
	 * Sets the currently active turtle
	 * @param aActiveTurtleIndex
	 */
	public void setTurtleAsCurrentlyActive(int aActiveTurtleIndex)
	{
		myCurrentlyActiveTurtleIndex = aActiveTurtleIndex;
		myCurrentlyActiveTurtle = myActiveTurtles.get(aActiveTurtleIndex);
	}
	
	/**
	 * Gets the currently active turtles
	 * @return
	 */
	public Collection<Turtle> getCurrentlyActiveTurtles() {
		return myActiveTurtles.values();
	}
	
	/**
	 * Gets the current turtle
	 * @return
	 */
	public Turtle getCurrentTurtle() {
		return myCurrentlyActiveTurtle;
	}
	
	/**
	 * Sets the next turtle as active
	 */
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
	
	/**
	 * Number of turtles that are on the screen
	 * @return
	 */
	public int numberOfTurtlesCreated() {
		return myTurtles.keySet().size();
	}
	
	/**
	 * check to see if the the active turtle index
	 * @return
	 */
	public boolean activeTurtleIndexHasBeenSetToStart()
	{
		ArrayList<Integer> indices = new ArrayList<Integer>(myActiveTurtles.keySet());
		int firstIndex = indices.get(0);
		return myCurrentlyActiveTurtleIndex == firstIndex;
	}
	
	/**
	 * Sees in the Turtle exists
	 * 
	 * @param aIndex
	 * @return
	 */
	public boolean containsTurtles(int aIndex) {
		return myTurtles.containsKey(aIndex);
	}
	
	/**
	 * Get the most recent robot 
	 */
	public IViewableRobot getMostRecentRobot(){
		return myMostRecentlyCreatedTurtle;
	}
	
	/**
	 * Add a turtle to the screen
	 * @param aIndex
	 */
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
	
	@Override
	public double getPreviousRotation() {
		return myCurrentlyActiveTurtle.getPreviousRotation();
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
	    if(myCurrentlyActiveTurtle != null)
	        myCurrentlyActiveTurtle.setPenInformation(aPenInformation);
	    else {
	        myActiveTurtles.get(0).setPenInformation(aPenInformation);
	    }
	}

	@Override
	public void setImageID(int aImageID) {
		myCurrentlyActiveTurtle.setImageID(aImageID);
	}


	/**
	 * Added Turtles to a Scope 
	 * 
	 */
	public void addTemporaryTurtleScope()
	{
		myCurrentActiveTurtleStack.push(myCurrentlyActiveTurtle);
		
		myActiveTurtleStack.push(new HashMap<Integer, Turtle>());
		myActiveTurtles = myActiveTurtleStack.peek();
	}
	
	/**
	 * Removed Turtles from a Scope
	 */
	public void removeTemporaryTurtleScope()
	{
		myActiveTurtleStack.pop();
		myActiveTurtles = myActiveTurtleStack.peek();

		setTurtleAsCurrentlyActive(myCurrentActiveTurtleStack.pop().getTurtleID());
	}
}