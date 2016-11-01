package back_end.model.robot;

import java.awt.Point;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import integration.drawing.PenInformation;
import integration.observe.Observable;


public class Turtle extends Observable implements IViewableRobot {
	
	private static final String DEFAULT = "resources.defaultvalues.DefaultValues";

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
        myXpos = Double.parseDouble(myDefaultResource.getString("TurtleXpos"));
        myYpos = Double.parseDouble(myDefaultResource.getString("TurtleYpos"));
        myPenInformation = new PenInformation();
        myImageID = Integer.parseInt(myDefaultResource.getString("TurtleImageID"));
        myTurtleID = aID;
    }

    /** SETTERS **/

    public void setCoordinates (double x, double y) {
        myXpos = x;
        notifyObservers();

    }

    public void setRotation (double r) {
        myRotation = r;
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

    public Point getCoordinate () {
    	Point coordinates = new Point(); 
    	coordinates.setLocation(myXpos, myYpos);
        return coordinates;
    }
    
	public int getImageID() {
		return myImageID;
	}

	public PenInformation getPenInformation() {
		return myPenInformation;
	}

    public double getRotation () {
        return myRotation;
    }

    public boolean isVisible () {
        return myVisibility;
    }

	@Override
	public int getTurtleID() {
		return myTurtleID;
	}
    



}
