package back_end.model.robot;

import integration.observe.IObservable;

/**
 * Building upon observable, this specifies all of the publically viewable properties of the robot class
 * 
 * @author Hannah Fuchshuber
 *
 */

public interface IViewRobot extends IObservable {

    /** GETTERS **/

    public double getX ();

    public double getY ();

    /**
     * @return current bearing of Robot
     */
    public double getRotation ();

    /**
     * @return boolean of Pen Down status
     */
    public boolean isPenDown ();

    /**
     * @return boolean of visibility status
     */
    public boolean isVisible ();

}
