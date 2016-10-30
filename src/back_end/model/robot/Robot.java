package back_end.model.robot;

/**
 * Allows for the model to set the values of the robot
 * 
 * @author Hannah Fuchshuber
 *
 */

public interface Robot extends IViewRobot {

    /** SETTERS **/

    public void setX (double x);

    public void setY (double y);

    public void setRotation (double r);

    public void setPenDown (boolean t);

    public void setVisible (boolean t);

}
