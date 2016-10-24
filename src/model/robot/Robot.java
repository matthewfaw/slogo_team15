package model.robot;

import integration.IObserver;

/**
 * Allows for the model to set the values of the robot
 * 
 * @author Hannah Fuchshuber
 *
 */

public interface Robot {

	/**SETTERS**/
	
	public void setX(double x);
	
	public void setY(double y);
	
	public void setRotation(double r);
	
	public void setPenDown(boolean t);
	
	public void setVisible(boolean t);
	
	/**GETTERS**/
	
	public double getX();
	
	public double getY();
	
	public double getRotation();
	
	public boolean isPenDown();
	
	public boolean isVisible();
	
	
}