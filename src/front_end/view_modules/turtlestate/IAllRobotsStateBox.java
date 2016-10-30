package front_end.view_modules.turtlestate;

import back_end.model.robot.IViewRobot;
import front_end.view_modules.ILanguageSwitcher;
import front_end.view_modules.IRobotAcceptor;
import front_end.view_modules.IViewModule;
import integration.observe.IObserver;

public interface IAllRobotsStateBox extends IViewModule, ILanguageSwitcher, IRobotAcceptor {
	
	public void switchRobotTabs(int aRobotID);
	
}
