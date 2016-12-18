package back_end.model.robot;

import java.awt.Point;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import back_end.model.exception.ReflectionException;
import integration.drawing.PenInformation;
import integration.observe.AbstractObservable;

/** 
 * The information class for a single turtle - holds all the data of a Turtle
 *
 */

public class Turtle extends AbstractObservable implements IRobot, IViewableRobot {
	
	private static final String DEFAULT = "resources.defaultvalues.DefaultValues";
	private static final String ERROR = "resources.errormessages.ErrorMessages";
	
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
	public ResourceBundle myErrorMessageResources; 

    public Turtle (int aID) {
        myDefaultResource = PropertyResourceBundle.getBundle(DEFAULT);
		myErrorMessageResources = ResourceBundle.getBundle(ERROR);
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
	public IRobot cloneThis() throws ReflectionException
	{
		Turtle turtle = new Turtle(this.myTurtleID);
		Field[] f = getClass().getDeclaredFields();
		for (Field field: f) {
			if (!Modifier.isStatic(field.getModifiers())) {
				try {
					field.set(turtle, field.get(this));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new ReflectionException(MessageFormat.format(myErrorMessageResources.getString("FailedTurtleCloning"), field.getName()));
				}
			}
		}
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

	@Override
	public void destroy() {
		removeAllObservers();
	}

}
