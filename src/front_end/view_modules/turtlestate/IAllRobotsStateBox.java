package front_end.view_modules.turtlestate;

import front_end.view_modules.IRobotAcceptor;
import front_end.view_modules.IViewModule;
import front_end.view_modules.image_color_module.interfaces.IImageColorModule;

public interface IAllRobotsStateBox extends IViewModule, IRobotAcceptor {
	
	public void switchRobotTabs(int aRobotID);
	
}
