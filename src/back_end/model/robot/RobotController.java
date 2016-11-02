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
	private List<Turtle> myTellActiveTurtles;
	private Turtle myMostRecentlyCreatedTurtle;
	
	public RobotController() {
		myTurtles = new HashMap<Integer, Turtle>();
		myTellActiveTurtles = new ArrayList<Turtle>();
		myActiveTurtles = new ArrayList<Turtle>();
		addTurtle(INITIAL_TURTLE_INDEX);
		addActiveTurtle(INITIAL_TURTLE_INDEX);
		setTurtleAsCurrentlyActive(INITIAL_TURTLE_INDEX);
		//currentTurtle = 1;
		//setActiveTurtles(new int[]{1}, true);
	}
	
	public void addActiveTurtle(int aTotalTurtleListIndex)
	{
		Turtle turtle = myTurtles.get(aTotalTurtleListIndex);
		
		if (!myActiveTurtles.contains(turtle)) {
			myActiveTurtles.add(turtle);
		}
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
//	@Override
//	public void setActiveTurtles(int[] aTurtleIDs, boolean aTellCommand) {
//		for (int i = 0; i < aTurtleIDs.length; i++) {
//			if (aTurtleIDs[i] >= getNumberOfTurtles()) {
//				addTurtle(aTurtleIDs[i]);
//			}
//		}
//		for (int i = 0; i < aTurtleIDs.length; i++) {
//			myActiveTurtles.add(myTurtles.get(aTurtleIDs[i]));
//		}
//		if (aTellCommand) {
//			myTellActiveTurtles.clear();
//			myTellActiveTurtles.addAll(myActiveTurtles);
//			currentTurtle = aTurtleIDs[aTurtleIDs.length - 1];
//		}
//	}
//	
//	@Override
//	public void endTemporaryActiveTurtles() {
//		myActiveTurtles.clear();
//		myActiveTurtles.addAll(myTellActiveTurtles);
//	}
	
	
	private void addTurtle(int aTurtleID) {
		createTurtleWithIndex(aTurtleID);
	}
	
	public IViewableRobot getMostRecentRobot(){
		return myMostRecentlyCreatedTurtle;
	}
	private void createTurtleWithIndex(int aIndex)
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
	public int getCurrentID() {
		return myCurrentlyActiveTurtle.getTurtleID();
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
	public double getRotation() {
		return myCurrentlyActiveTurtle.getRotation();
	}

	@Override
	public boolean isVisible() {
		return myCurrentlyActiveTurtle.isVisible();
	}

	/**SETTERS**/ 
	
	@Override
	public void setCoordinates(double x, double y) {
//		for (int i = 0; i < myActiveTurtles.size(); i++) {
//			myActiveTurtles.get(i).setCoordinates(x, y);
//		}
		myCurrentlyActiveTurtle.setCoordinates(x,y);
	}

	@Override
	public void setRotation(double r) {
//		for (int i = 0; i < myActiveTurtles.size(); i++) {
//			myActiveTurtles.get(i).setRotation(r);
//		}
		myCurrentlyActiveTurtle.setRotation(r);
	}

	@Override
	public void setVisible(boolean t) {
//		for (int i = 0; i < myActiveTurtles.size(); i++) {
//			myActiveTurtles.get(i).setVisible(t);
//		}
		myCurrentlyActiveTurtle.setVisible(t);
	}

	@Override
	public void setPenInformation(PenInformation aPenInformation) {
//		for (int i = 0; i < myActiveTurtles.size(); i++) {
//			myActiveTurtles.get(i).setPenInformation(aPenInformation);
//		}
		myCurrentlyActiveTurtle.setPenInformation(aPenInformation);
	}

	@Override
	public void setImageID(int aImageID) {
//		for (int i = 0; i < myActiveTurtles.size(); i++) {
//			myActiveTurtles.get(i).setImageID(aImageID);
//		}
		myCurrentlyActiveTurtle.setImageID(aImageID);
	}

}
