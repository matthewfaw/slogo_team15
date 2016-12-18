package integration.router;

import back_end.model.states.IViewableMethodState;
import back_end.model.states.IViewableVariableState;
import back_end.model.states.background.IViewableBackground;
import back_end.model.states.background.IViewableColorPalette;
import back_end.model.states.methodhistory.IViewableUserInputHistory;
import front_end.sender.IColorSender;
import front_end.sender.IRobotSender;


/**
 *  Routes the relevant state objects from the back end to the front end.
 *  Middle Man to accommodate for multiple turtle/variable scenario.
 *  <P>
 *  IMPLEMENTATION NOTE:
 *  Router must make final decision upon instantiation of which front end 
 *  modules set as destinations for distributions.
 *   
 * @author George Bernard
 */
public interface IRouter extends IRobotRouter, IErrorRouter {

	/**
	 * distributes RobotSenders to front end elements
	 * 
	 * @see IRobotSender
	 * @param aRoboSender
	 */
	public void distributeRobotSenders(IRobotSender aRoboSender);
	
	/**
	 * distributes ColorSenders to front end elements
	 * 
	 * @see IColorSender
	 * @param aColorSender
	 */
	public void distributeColorSenders(IColorSender aColorSender);
	
	/**
	 * distributes VariableMap to modules decided upon inside Router
	 * 
	 * @see IViewableVariableState
	 * @param aViewVariableState
	 */
	public void distributeVariableMap(IViewableVariableState aViewVariableState);
	
	/**
	 * distributes History to modules decided upon inside Router
	 * 
	 * @see IViewableUserInputHistory
	 * @see aHistory
	 */
	public void distributeHistory(IViewableUserInputHistory aHistory);
	
	/**
	 * distributes Function to modules decided upon inside Router
	 * 
	 * @see IViewableMethodState
	 * @see aMethod
	 */
	public void distributeFunction(IViewableMethodState aMethod);

	
	/**
	 * 
	 * @see IViewableBackground
	 * @param aViewBackground
	 */
	public void distributeBackground( IViewableBackground aViewBackground );
	
	/**
	 * 
	 * @see IViewableColorPalette
	 * @param aViewColorPalette
	 */
	public void distributeColorPalette( IViewableColorPalette aViewColorPalette ); 
	
	/**
	 * 
	 * @see IViewableMethodState
	 * @param aViewableMethods
	 */
	public void distributeMethods ( IViewableMethodState aViewableMethods ); 
	
}
