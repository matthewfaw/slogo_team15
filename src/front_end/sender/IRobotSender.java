package front_end.sender;

import integration.drawing.PenInformation;

/**
 * This interface delineates everything the front_end needs 
 * to tell the backend to either change or be updated with 
 * apropos of robots
 * 
 * @see front_end.sender
 * @author George Bernard
 */
public interface IRobotSender {
	
	/**
	 * Sets the Visibility of the robot with given id
	 * 
	 * @param aID
	 * @param isVisible
	 */
	public void setVisibility(int aID, boolean isVisible);
	
	/**
	 * Sets the pen information of the turtle with given ID
	 *
	 * @see PenInformation
	 * @param aID
	 * @param aPenInformation
	 */
	public void setPenInformation(int aID, PenInformation aPenInformation);
	
}
