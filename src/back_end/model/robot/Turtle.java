package back_end.model.robot;

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
	
	public Turtle() {
		myObservers = new ArrayList<IRobotObserver>();
		
		//TODO: Move this to a resource file, and have the
		// constructor initialize these vals
		myVisibility = true;
		myXpos = 0.0;
		myYpos = 0.0;
		myPenDown = true;
	}

	/** SETTERS **/
	
	@Override
	public void setX(double x) {
		myXpos = x;
	              notifyObservers();

		
	}

	@Override
	public void setY(double y) {
		myYpos = y;
                notifyObservers();
		
	}
	
	@Override
	public void setRotation(double r) {
		myRotation = r;
	              notifyObservers();

	}

	@Override
	public void setPenDown(boolean t) {
		myPenDown = t;
		
	}

	@Override
	public void setVisible(boolean t) {
		myVisibility = t;
	              notifyObservers();

	}
	
	
	
	/** GETTERS **/

	@Override
	public double getX() {
		return myXpos;
	}

	@Override
	public double getY() {
		return myYpos;
	}

	@Override
	public double getRotation() {
		return myRotation;
	}

	@Override
	public boolean isPenDown() {
		return myPenDown;
	}

	@Override
	public boolean isVisible() {
		return myVisibility;
	}

	/** OBSERVERABLE **/
	
	@Override
	public void registerObserver(IRobotObserver o) {
		myObservers.add(o);
		o.giveRobot(this);
	}

	@Override
	public void removeObserver(IRobotObserver o) {
		int i = myObservers.indexOf(o);
		if (i > 0) {
			myObservers.remove(i);
		}
		
	}

	@Override
	public void notifyObservers() {
		for (IRobotObserver observer : myObservers) {
			observer.update();
		}
	}


}
