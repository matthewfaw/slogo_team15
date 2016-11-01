package front_end.view_modules.turtleBox.turtleMovement;

import back_end.model.robot.IViewRobot;
import front_end.view_modules.turtleBox.ITurtleBox;
import integration.observe.IObserver;
import javafx.scene.image.ImageView;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class TurtleMovement implements IObserver {

    IViewRobot myRobot;

    private ImageView myTurtle;
    private int myWidth;
    private int myHeight;
    private PenMovement myPenMovement;

    public static final int FRAMES_PER_SECOND = 60;
    private ITurtleBox myTurtleBox;

    public TurtleMovement (ITurtleBox aConTurt, IViewRobot aRobot, int aWidth, int aHeight) {
        myPenMovement = new PenMovement(this);
        myTurtleBox = aConTurt;
        myWidth = aWidth;
        myHeight = aHeight;
        myRobot = aRobot;
        myRobot.registerObserver(this);
    }

    @Override
    public void update () {

        myTurtle = myTurtleBox.getTurtle();
        myRobot = myTurtleBox.getRobot();

        checkVisibility();

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
            myPenMovement.drawWithPen(myTurtleBox);
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
