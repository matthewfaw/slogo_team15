package front_end.view_modules.turtlestate;

import front_end.view_modules.ILanguageSwitcher;
import front_end.view_modules.IViewModule;
import integration.acceptors.IRobotAcceptor;

public interface IAllRobotsStateBox extends IViewModule, ILanguageSwitcher, IRobotAcceptor {
	
	public void switchRobotTabs(int aRobotID);
	
}
