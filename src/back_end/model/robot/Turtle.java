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
	
	public Turtle() {
		myObservers = new ArrayList<IObserver>();
	}

	/** SETTERS **/
	
	@Override
	public void setX(double x) {
		notifyObservers();
		myXpos = x;
		
	}

	@Override
	public void setY(double y) {
		notifyObservers();
		myYpos = y;
		
	}
	
	@Override
	public void setRotation(double r) {
		notifyObservers();
		myRotation = r; 
	}

	@Override
	public void setPenDown(boolean t) {
		myPenDown = t;
		
	}

	@Override
	public void setVisible(boolean t) {
		notifyObservers();
		myVisibility = t;
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

	@Override
	public void registerObserver(IObserver o) {
		myObservers.add(o);
		
	}

	@Override
	public void removeObserver(IObserver o) {
		int i = myObservers.indexOf(o);
		if (i > 0) {
			myObservers.remove(i);
		}
		
	}

	@Override
	public void notifyObservers() {
		for (IObserver observer : myObservers) {
			observer.update();
		}
		
	}
	
	/** OBSERVERABLE **/
	



}
