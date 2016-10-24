package integration.observe;

import back_end.model.robot.IViewRobot;

public interface IRobotObserver {
	
	/**
	 * Every time update is called, the robot has changed state. 
	 * And whatever observes this robot must adjust for it
	 */
	void update();
	
	void giveRobot(IViewRobot aViewRobot);
	
}
