package model.robot;

/**
 * Allows for the view to get the values set by the back-end
 * 
 * @author Hannah Fuchshuber
 *
 */

public interface IViewRobot {

	/**GETTERS**/
	
	public double getX();
	
	public double getY();
	
	public double getRotation();
	
	public boolean isPenDown();
	
	public boolean isVisible();
	
}
