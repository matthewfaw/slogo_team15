package front_end.view_modules.turtleBox.turtleMovement;

import back_end.model.robot.IViewRobot;
import front_end.view_modules.turtleBox.ITurtleBox;
import javafx.scene.image.ImageView;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class TurtleMovement implements ITurtleMovement {

    IViewRobot myRobot;

    private ImageView myTurtle;
    private int myWidth;
    private int myHeight;

    public static final int FRAMES_PER_SECOND = 60;
    private ITurtleBox myTurtleBox;

    public TurtleMovement (ITurtleBox myConTurt, int width, int height) {
        myTurtleBox = myConTurt;
        myWidth = width;
        myHeight = height;
    }

    @Override
    public void updateTurtle () {

        myTurtle = myTurtleBox.getTurtle();
        myRobot = myTurtleBox.getRobot();

        checkVisibility();
        // This is a problem: what if turtle needs to rotate first?
        // Unless that is the function of observer/observable
        updateTurtlePosition();

        updateTurtleRotation();
    }

    private void updateTurtleRotation () {
        myTurtle.setRotate(myRobot.getRotation());
    }

    private void updateTurtlePosition () {
        moveTurtleX();
        moveTurtleY();
        if (myRobot.isPenDown()) {
            // TODO: Get this to access pen movement class
        }
    }

    private void checkVisibility () {
        if (!myRobot.isVisible()) {
            myTurtleBox.removeTurtle();
        }
        else {
            myTurtleBox.showTurtle();
        }
    }

    private double translatedXCoordinate () {
        return myRobot.getX() + myWidth / 2;
    }

    private double translatedYCoordinate () {
        return -myRobot.getY() + myHeight / 2;
    }

    private void moveTurtleX () {
        myTurtle.setX(translatedXCoordinate());
    }

    private void moveTurtleY () {
        myTurtle.setY(translatedYCoordinate());
    }

}
