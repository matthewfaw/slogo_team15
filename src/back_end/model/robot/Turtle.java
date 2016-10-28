package back_end.model.robot;

import java.awt.Point;
import java.util.ArrayList;
import integration.observe.IObservable;
import integration.observe.IRobotObserver;


public class Turtle implements Robot, IViewRobot, IObservable {

    private double myXpos;
    private double myYpos;
    private double myRotation;
    private boolean myPenDown;
    private boolean myVisibility;
    private ArrayList<IRobotObserver> myObservers;
    private int myTurtleID;
    private int myImageID;
    //private PenStyle myPenStyle;

    public Turtle () {
        myObservers = new ArrayList<IRobotObserver>();

        // TODO: Move this to a resource file, and have the
        // constructor initialize these vals
        myVisibility = true;
        myXpos = 0.0;
        myYpos = 0.0;
        myPenDown = false;
        // myPenStyle = new PenStyle();
        myImageID = 0;
        myTurtleID = 0;
    }

    /** SETTERS **/

    @Override
    public void setX (double x) {
        myXpos = x;
        notifyObservers();

    }

    @Override
    public void setY (double y) {
        myYpos = y;
        notifyObservers();

    }

    @Override
    public void setRotation (double r) {
        myRotation = r;
        notifyObservers();

    }

    @Override
    public void setPenDown (boolean t) {
        myPenDown = t;

    }

    @Override
    public void setVisible (boolean t) {
        myVisibility = t;
        notifyObservers();
    }
    
	//@Override
	//public void setPenStyle(PenStyle aPenStyle) {
	//	myPenStyle = aPenStyle;
	//	
	//}

	@Override
	public void setTurtleID(int aTurtleID) {
		myTurtleID = aTurtleID;
	}
	

	@Override
	public void setImageID(int aImageID) {
		myImageID = aImageID;
		
	}

    /** GETTERS **/

    @Override
    public Point getCoordinates () {
    	Point coordinates = new Point(); 
    	coordinates.setLocation(myXpos, myYpos);
        return coordinates;
    }
    
	@Override
	public int getImageID() {
		return myImageID;
	}

	//@Override
	//public PenStyle getPenStyle() {
	//	return myPenStyle;
	//}

    @Override
    public double getRotation () {
        return myRotation;
    }

    @Override
    public boolean isPenDown () {
        return myPenDown;
    }

    @Override
    public boolean isVisible () {
        return myVisibility;
    }
    
    @Override
	public int getTurtleID() {
		return myTurtleID;
	}

    /** OBSERVERABLE **/

    @Override
    public void registerObserver (IRobotObserver o) {
        myObservers.add(o);
        o.giveRobot(this);
    }

    @Override
    public void removeObserver (IRobotObserver o) {
        int i = myObservers.indexOf(o);
        if (i > 0) {
            myObservers.remove(i);
        }

    }

    @Override
    public void notifyObservers () {
        for (IRobotObserver observer : myObservers) {
            observer.update();
        }
    }


}
