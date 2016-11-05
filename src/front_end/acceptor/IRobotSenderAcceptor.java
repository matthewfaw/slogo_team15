package front_end.acceptor;

import front_end.sender.IRobotSender;
/**
 * Defines the interface for accepting the robot sender
 * 
 * @see front_end.sender.IRobotSender  
 * @author George Bernard
 */
public interface IRobotSenderAcceptor {

	public void giveRobotSender(IRobotSender aRoboSender);
	
}
