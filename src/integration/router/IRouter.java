package integration.router;

import back_end.model.robot.IViewRobot;
import back_end.model.states.IViewVariableState;

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
	public void distributeVariableMap(IViewVariableState aViewVariableState);
	
	/**
	 * distributes History to modules decided upon inside Router
	 */
	public void distributeHistory(/**TODO History Object (no observer relation) **/);
	
	/**
	 * distributes Function to modules decided upon inside Router
	 */
	public void distributeFunction(/**TODO Function Object (no observer relation) **/);
	
	/**
	 * distributes Error to modules decided upon inside Router
	 */
	public void distributeError( Exception aException );
	
}
