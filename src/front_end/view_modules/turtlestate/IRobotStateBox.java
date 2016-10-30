package front_end.view_modules.turtlestate;

import front_end.view_modules.ILanguageSwitcher;
import front_end.view_modules.IViewModule;
import integration.acceptors.IRobotAcceptor;
import integration.observe.IObserver;

public interface IRobotStateBox extends IViewModule, 
										ILanguageSwitcher, 
										IObserver,
										IRobotAcceptor
										{
	
	public int getRobotID();
}
