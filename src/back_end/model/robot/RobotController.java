package back_end.model.robot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import integration.drawing.PenInformation;
import integration.observe.Observable;

public class RobotController extends Observable implements IRobot {
	private static final int INITIAL_TURTLE_INDEX = 0;
	
	private HashMap<Integer, Turtle> myTurtles;
	private Turtle myCurrentlyActiveTurtle;
	private int myCurrentlyActiveTurtleIndex;
	private List<Turtle> myActiveTurtles;
	private Turtle myMostRecentlyCreatedTurtle;
	
	public RobotController() {
		myTurtles = new HashMap<Integer, Turtle>();
		myActiveTurtles = new ArrayList<Turtle>();
		addTurtle(INITIAL_TURTLE_INDEX);
		addActiveTurtle(INITIAL_TURTLE_INDEX);
		setTurtleAsCurrentlyActive(INITIAL_TURTLE_INDEX);
	}
	
	public void addActiveTurtle(int aTotalTurtleListIndex)
	{
		Turtle turtle = myTurtles.get(aTotalTurtleListIndex);
		
		if (!myActiveTurtles.contains(turtle)) {
			myActiveTurtles.add(turtle);
		}
	}
	
	public void clearActiveTurtles() {
		myActiveTurtles = new ArrayList<Turtle>();
	}
	
	public void setTurtleAsCurrentlyActive(int aActiveTurtleIndex)
	{
		myCurrentlyActiveTurtleIndex = aActiveTurtleIndex;
		myCurrentlyActiveTurtle = myActiveTurtles.get(aActiveTurtleIndex);
	}
	
	public List<Turtle> getCurrentlyActiveTurtles()
	{
		return myActiveTurtles;
	}
	public Turtle getCurrentTurtle()
	{
		return myCurrentlyActiveTurtle;
	}
	
	public void setNextTurtleAsActive()
	{
		int newIndex = (myCurrentlyActiveTurtleIndex + 1) % myActiveTurtles.size();
		setTurtleAsCurrentlyActive(newIndex);
	}
	
	public boolean activeTurtleIndexHasBeenSetToStart()
	{
		return myCurrentlyActiveTurtleIndex == 0;
	}
	
	
	public IViewableRobot getMostRecentRobot(){
		return myMostRecentlyCreatedTurtle;
	}
	
	public void addTurtle(int aIndex)
	{
		Turtle turtle = new Turtle(aIndex);
		myMostRecentlyCreatedTurtle = turtle;
		notifyObservers();
		myTurtles.put(aIndex, turtle);
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
