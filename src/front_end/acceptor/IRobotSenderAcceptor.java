package front_end.acceptor;

import front_end.sender.IRobotSender;

/**
 * Defines the interface for accepting the robot sender
 * 
 * @author George Bernard
 */
public interface IRobotSenderAcceptor {

	/**
	 * Pushes RobotSender object to this object
	 * 
	 * @see front_end.sender.IRobotSender  
	 * @param aRoboSender
	 */
	public void giveRobotSender(IRobotSender aRoboSender);
	
}
