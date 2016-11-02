package front_end.view_modules.turtlestate;

import front_end.acceptor.IRobotAcceptor;
import front_end.acceptor.IRobotSenderAcceptor;
import front_end.view_modules.IViewModule;

public interface IAllRobotsStateBox extends IViewModule, IRobotAcceptor, IRobotSenderAcceptor {
	
	public void switchRobotTabs(int aRobotID);
	
	
}
