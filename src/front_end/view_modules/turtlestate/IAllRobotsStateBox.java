package front_end.view_modules.turtlestate;

import front_end.acceptor.IRobotAcceptor;
import front_end.acceptor.IRobotSenderAcceptor;
import front_end.view_modules.IViewModule;

/**
 * Specifies all of the interfaces that an State box for all robots must use.
 * 
 * @author George Bernard
 */
public interface IAllRobotsStateBox extends IViewModule, IRobotAcceptor, IRobotSenderAcceptor {
	
	/**
	 * Switches tabs of the AllRobotStateBox to the given ID operand.
	 * The viewer must show the robot with the given
	 * 
	 * @param aRobotID
	 */
	public void switchRobotTabs(int aRobotID);
	
	
}
