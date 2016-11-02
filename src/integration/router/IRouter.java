package integration.router;

import back_end.model.exception.IExceptionDebugger;
import back_end.model.robot.IViewableRobot;
import back_end.model.states.IViewableVariableState;
import back_end.model.states.background.IViewableBackground;
import back_end.model.states.background.IViewableColorPalette;
import back_end.model.states.methodhistory.IViewableUserInputHistory;
import front_end.sender.IColorSender;
import front_end.sender.IRobotSender;


/**
 *  Routes the relevant state objects from the back end to the front end
 *   
 *   Router must make final decision upon instantiation of which front end 
 *   modules set as destinations for distributions 
 *   
 * @author George Bernard
 */
public interface IRouter {

	public void distributeRobotSenders(IRobotSender aRoboSender);
	
	public void distributeColorSenders(IColorSender aColorSender);
	
	/**
	 * distributes Robot to modules decided upon inside Router
	 * 
	 * @param aViewRobot
	 */
	public void distributeRobot(IViewableRobot aViewRobot);
	
	/**
	 * distributes VariableMap to modules decided upon inside Router
	 */
	public void distributeVariableMap(IViewableVariableState aViewVariableState);
	
	/**
	 * distributes History to modules decided upon inside Router
	 */
	public void distributeHistory(IViewableUserInputHistory aHistory);
	
	/**
	 * distributes Function to modules decided upon inside Router
	 */
	public void distributeFunction(/**TODO Function Object (no observer relation) **/);
	
	/**
	 * distributes Error to modules decided upon inside Router
	 * 
	 * @param passes in exception from back end
	 */
	public void distributeError( IExceptionDebugger aException );

	public void distributeBackground( IViewableBackground aViewBackground );
	
	public void distributeColorPalette( IViewableColorPalette aViewColorPalette ); 
}
