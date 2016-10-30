package back_end.model.robot;

import java.util.ArrayList;

import integration.observe.IObservable;
import integration.observe.IObserver;


public class Turtle implements Robot, IViewRobot, IObservable {

    private double myXpos;
    private double myYpos;
    private double myRotation;
    private boolean myPenDown;
    private boolean myVisibility;
    private ArrayList<IObserver> myObservers;

    public Turtle () {
        myObservers = new ArrayList<IObserver>();

        // TODO: Move this to a resource file, and have the
        // constructor initialize these vals
        myVisibility = true;
        myXpos = 0.0;
        myYpos = 0.0;
        myPenDown = false;
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

    /** GETTERS **/

    @Override
    public double getX () {
        return myXpos;
    }

    @Override
    public double getY () {
        return myYpos;
    }

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

    /** OBSERVERABLE **/

    @Override
    public void registerObserver (IObserver o) {
        myObservers.add(o);
    }

    @Override
    public void removeObserver (IObserver o) {
        int i = myObservers.indexOf(o);
        if (i > 0) {
            myObservers.remove(i);
        }

    }

    @Override
    public void notifyObservers () {
        for (IObserver observer : myObservers) {
            observer.update();
        }
    }

}
