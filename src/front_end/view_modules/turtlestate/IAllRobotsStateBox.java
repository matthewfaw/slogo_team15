package front_end.view_modules.turtlestate;

import back_end.model.robot.IViewRobot;
import front_end.view_modules.ILanguageSwitcher;
import front_end.view_modules.IViewModule;

public interface IAllRobotsStateBox extends IViewModule, ILanguageSwitcher {

	public void giveRobot(IViewRobot aViewRobot);
	
}
