package integration.router;

import back_end.model.robot.IViewRobot;

/**
 *  Routes the relevant state objects from the back end to the front end
 *   
 *   Router must make final decision upon instantiation of which front end 
 *   modules set as destinations for distributions 
 *   
 * @author George Bernard
 */
public interface IRouter {
	
	/**
	 * distributes Robot to modules decided upon inside Router
	 * 
	 * @param aViewRobot
	 */
	public void distributeRobot(IViewRobot aViewRobot);
	
	/**
	 * distributes VariableMap to modules decided upon inside Router
	 */
	public void distributeVariableMap();
	
}
