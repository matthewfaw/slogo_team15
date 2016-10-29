package back_end.model.robot;


import java.awt.Point;
import integration.observe.IObservable;

/**
 * Allows for the view to get the values set by the back-end
 * 
 * @author Hannah Fuchshuber
 *
 */

public interface IViewRobot extends IObservable {

    /** GETTERS **/

    public Point getCoordinate ();
           
    public int getImageID ();
    
    //public PenStyle getPenStyle ();
    
    public int getTurtleID ();

    public double getRotation ();

    public boolean isPenDown ();

    public boolean isVisible ();
    

}
