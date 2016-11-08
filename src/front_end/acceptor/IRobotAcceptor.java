package front_end.acceptor;

import back_end.model.robot.IViewableRobot;

/**
 * Defines the RobotAcceptor Property. 
 * 
 * Implemented by any module that needs to have 
 * updating robot pushed to it. 
 * 
 * @see front_end.acceptor;
 * @author George Bernard
 */
public interface IRobotAcceptor {
	
	/**
	 * Pushes specified robot to object
	 * 
	 * @see back_end.model.robot.IViewableRobot
	 * @param aViewRobot
	 */
	public void giveRobot(IViewableRobot aViewRobot);
	
}
