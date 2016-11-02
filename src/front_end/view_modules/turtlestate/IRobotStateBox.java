package front_end.view_modules.turtlestate;

import front_end.view_modules.IViewModule;
import integration.observe.IObserver;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public interface IRobotStateBox extends IViewModule, IObserver {

    public int getRobotID ();
    
}
