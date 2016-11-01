package front_end.view_modules.turtlestate;

import front_end.acceptor.IRobotAcceptor;
import front_end.view_modules.IViewModule;

public interface IAllRobotsStateBox extends IViewModule, IRobotAcceptor {
	
	public void switchRobotTabs(int aRobotID);
	
}
