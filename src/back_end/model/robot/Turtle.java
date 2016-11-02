package back_end.model.robot;

import java.awt.Point;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import integration.drawing.PenInformation;
import integration.observe.Observable;


public class Turtle extends Observable implements IViewableRobot {
	
	private static final String DEFAULT = "resources.defaultvalues.DefaultValues";

	private double myPreviousXPosition;
	private double myPreviousYPosition;
	private double myPreviousRotation;
    private double myXpos;
    private double myYpos;
    private double myRotation;
    private boolean myVisibility;
    private int myTurtleID;
    private int myImageID;
    private PenInformation myPenInformation;
    private ResourceBundle myDefaultResource;

    public Turtle (int aID) {
        myDefaultResource = PropertyResourceBundle.getBundle(DEFAULT);
        myVisibility = Boolean.parseBoolean(myDefaultResource.getString("TurtleVisibility"));
        myPreviousXPosition = Double.parseDouble(myDefaultResource.getString("TurtleXpos"));
        myPreviousYPosition = Double.parseDouble(myDefaultResource.getString("TurtleYpos"));
        myXpos = Double.parseDouble(myDefaultResource.getString("TurtleXpos"));
        myYpos = Double.parseDouble(myDefaultResource.getString("TurtleYpos"));
        myPenInformation = new PenInformation();
        myImageID = Integer.parseInt(myDefaultResource.getString("TurtleImageID"));
        myTurtleID = aID;
        myRotation = Double.parseDouble(myDefaultResource.getString("TurtleRotation"));
    }

    /** SETTERS **/

    public void setCoordinates (double x, double y) {
    	myPreviousXPosition = myXpos;
    	myPreviousYPosition = myYpos;

        myXpos = x;
        myYpos = y;
        notifyObservers();

    }

    public void setRotation (double r) {
        myRotation = r % 360;
        notifyObservers();

    }

    public void setVisible (boolean t) {
        myVisibility = t;
        notifyObservers();
    }
    
	public void setPenInformation (PenInformation aPenInformation) {
		myPenInformation = aPenInformation;
	}
	

	public void setImageID(int aImageID) {
		myImageID = aImageID;
		
	}

    /** GETTERS **/

    public Point getCurrentCoordinate () {
    	return getNewPoint(myXpos, myYpos);
    }

	@Override
	public Point getPreviousCoordinate() {
		return getNewPoint(myPreviousXPosition, myPreviousYPosition);
	}
	
	private Point getNewPoint(double aX, double aY)
	{
    	Point coordinates = new Point(); 
    	coordinates.setLocation(aX, aY);
        return coordinates;
	}
    
    
	public int getImageID() {
		return myImageID;
	}

	public PenInformation getPenInformation() {
		return myPenInformation;
	}

    public double getCurrentRotation () {
        return myRotation;
    }

    public boolean isVisible () {
        return myVisibility;
    }

	@Override
	public int getTurtleID() {
		return myTurtleID;
	}

	@Override
	public double getPreviousRotation() {
		return myPreviousRotation;
	}




}
