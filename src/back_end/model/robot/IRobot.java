package back_end.model.robot;

import integration.drawing.PenInformation;

/**
 * Allows for the model to set the values of the robot
 * 
 * @author Hannah Fuchshuber
 *
 */

public interface IRobot extends IViewableRobot {

    /** SETTERS **/

    public void setCoordinates (double x, double y);

    public void setRotation (double r);

    public void setVisible (boolean t);

    public void setPenInformation (PenInformation aPenInformation);
        
    public void setImageID (int aImageID);
    
    public IViewableRobot getMostRecentRobot();

	//public void setTurtleID(int aTurtleID);
	
	public int getCurrentID();
	
	public int getNumberOfTurtles();
	
	public Turtle getTurtle(int aTurtleID);

	void setActiveTurtles(int[] aTurtleIDs, boolean aTellCommand);

	void endTemporaryActiveTurtles();
}
