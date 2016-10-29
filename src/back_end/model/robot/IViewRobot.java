package back_end.model.robot;

import integration.observe.IObservableRobot;

/**
 * Allows for the view to get the values set by the back-end
 * 
 * @author Hannah Fuchshuber
 *
 */

public interface IViewRobot extends IObservableRobot {

    /** GETTERS **/

    public double getX ();

    public double getY ();

    public double getRotation ();

    public boolean isPenDown ();

    public boolean isVisible ();

}
