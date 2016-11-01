package front_end.view_modules.turtlestate;

import front_end.acceptor.IRobotAcceptor;
import front_end.view_modules.IViewModule;
import integration.observe.IObserver;


public interface IRobotStateBox extends IViewModule,
        IObserver,
        IRobotAcceptor {

    public int getRobotID ();
}
