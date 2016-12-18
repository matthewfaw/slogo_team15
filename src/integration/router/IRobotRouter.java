package integration.router;

import back_end.model.robot.IViewableRobot;

public interface IRobotRouter {

	/**
	 * distributes Robot to modules decided upon inside Router
	 * 
	 * @see IViewableRobot
	 * @param aViewRobot
	 */
	public void distributeRobot(IViewableRobot aViewRobot);
}
