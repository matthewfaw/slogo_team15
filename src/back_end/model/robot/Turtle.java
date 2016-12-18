package back_end.model.robot;

import java.awt.Point;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import integration.drawing.PenInformation;
import integration.observe.AbstractObservable;
import integration.observe.IObserver;

/** 
 * The information class for a single turtle - holds all the data of a Turtle
 *
 */

public class Turtle extends AbstractObservable implements IRobot, IViewableRobot {
	
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
        myPreviousXPosition = Double.parseDouble(myDefaultResource.getString("TurtleXPosPrev"));
        myPreviousYPosition = Double.parseDouble(myDefaultResource.getString("TurtleYPosPrev"));
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
    	myPreviousRotation = myRotation;
        myXpos = x;
        myYpos = y;
        notifyObservers();

    }

    public void setRotation (double r) {
    	myPreviousXPosition = myXpos;
    	myPreviousYPosition = myYpos;
    	myPreviousRotation = myRotation;
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

	@Override
	public IRobot clone() {
//		Field[] f = getClass().getDeclaredFields();
//		for (Field field: f) {
//			if (!Modifier.isStatic(field.getModifiers())) {
//				field.
//				System.out.printf("%s %s %s\n", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName());
//			}
//		}
		Turtle turtle = new Turtle(this.myTurtleID);
		turtle.myPreviousXPosition = this.myPreviousXPosition;
		turtle.myPreviousYPosition = this.myPreviousYPosition;
		turtle.myPreviousRotation = this.myPreviousRotation;
    	turtle.myXpos = this.myXpos;
    	turtle.myYpos = this.myYpos;
    	turtle.myRotation = this.myRotation;
    	turtle.myVisibility = this.myVisibility;
    	turtle.myTurtleID = this.myTurtleID;
    	turtle.myImageID = this.myImageID;
    	turtle.myPenInformation = this.myPenInformation;
    	turtle.myDefaultResource = this.myDefaultResource;
    	
    	return turtle;
	}

	@Override
	//TODO: This method should not be part of IRobot
	public IViewableRobot getMostRecentRobot() {
		return null;
	}

	@Override
	//TODO: This method should not be part of IRobot
	public int getNumberOfTurtles() {
		return 0;
	}

	@Override
	//TODO: This method should not be part of the IRobot interface
	public Turtle getTurtle(int aTurtleID) {
		return null;
	}



	public static void main(String[] args)
	{
		Turtle t = new Turtle(1);
		t.clone();
	}

	@Override
	public void destroy() {
		removeAllObservers();
	}

}
