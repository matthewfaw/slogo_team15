package front_end.acceptor;

import back_end.model.robot.IViewableRobot;

/**
 * Defines the RobotAcceptor Property. 
 * 
 * Implemented by any module that needs to have 
 * robot pushed to it. 
 * 
 * @author George Bernard
 */
public interface IRobotAcceptor {
	
	/**
	 * Pushes specified robot to object
	 * 
	 * @param aViewRobot
	 */
	public void giveRobot(IViewableRobot aViewRobot);
	
}
