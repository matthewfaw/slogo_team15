package back_end.model.robot;

import java.awt.Point;

import integration.drawing.PenInformation;
import integration.observe.Observable;


public class Turtle extends Observable implements IViewableRobot {

    private double myXpos;
    private double myYpos;
    private double myRotation;
    private boolean myPenDown;
    private boolean myVisibility;
    private int myTurtleID;
    private int myImageID;
    private PenInformation myPenInformation;

    public Turtle () {
        // TODO: Move this to a resource file, and have the
        // constructor initialize these vals
        myVisibility = true;
        myXpos = 0.0;
        myYpos = 0.0;
        myPenDown = false;
        myPenInformation = new PenInformation();
        myImageID = 0;
        myTurtleID = 0;
    }

    /** SETTERS **/

    public void setX (double x) {
        myXpos = x;
        notifyObservers();

    }

    public void setY (double y) {
        myYpos = y;
        notifyObservers();

    }

    public void setRotation (double r) {
        myRotation = r;
        notifyObservers();

    }

    public void setPenDown (boolean t) {
        myPenDown = t;

    }

    public void setVisible (boolean t) {
        myVisibility = t;
        notifyObservers();
    }
    
	public void setPenInformation (PenInformation aPenInformation) {
		myPenInformation = aPenInformation;
	}

	public void setTurtleID(int aTurtleID) {
		myTurtleID = aTurtleID;
	}
	

	public void setImageID(int aImageID) {
		myImageID = aImageID;
		
	}

    /** GETTERS **/

    @Override
    public Point getCoordinate () {
    	Point coordinates = new Point(); 
    	coordinates.setLocation(myXpos, myYpos);
        return coordinates;
    }
    
	@Override
	public int getImageID() {
		return myImageID;
	}

	@Override
	public PenInformation getPenInformation() {
		return myPenInformation;
	}

    @Override
    public double getRotation () {
        return myRotation;
    }

    @Override
    public boolean isVisible () {
        return myVisibility;
    }
    
    @Override
	public int getTurtleID() {
		return myTurtleID;
	}



}
