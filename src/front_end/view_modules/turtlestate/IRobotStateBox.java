package front_end.view_modules.turtlestate;

import front_end.view_modules.IViewModule;
import integration.observe.IObserver;

/**
 * This interface delineates all of the needed methods for viewing for a single Robot
 * <p>
 * IMPLEMENTATION NOTE:
 * This should have a constructor that takes in an IViewableRobot for updating state.
 * 
 * @author George Bernard
 */
public interface IRobotStateBox extends IViewModule, IObserver {

    /**
     * Returns the turtle ID of the given robot
     * 
     * @return integer of TurtleID for given 
     */
    int getRobotID ();
    
}
