package front_end.view_modules.turtlestate;

import front_end.view_modules.IViewModule;
import integration.observe.IObserver;


public interface IRobotStateBox extends IViewModule, IObserver {

    public int getRobotID ();
}
