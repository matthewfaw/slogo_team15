package front_end.view_modules.turtleBox.turtleMovement;

import back_end.model.robot.IViewableRobot;
import front_end.view_modules.turtleBox.ITurtleBox;
import integration.observe.IObserver;
import javafx.scene.image.ImageView;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class TurtleMovement implements IObserver {

    IViewableRobot myRobot;

    private ImageView myImage;
    private int myWidth;
    private int myHeight;
    private PenMovement myPenMovement;

    public static final int FRAMES_PER_SECOND = 60;
    private ITurtleBox myTurtleBox;

    public TurtleMovement (ITurtleBox aTurtleBox, ImageView aImage, IViewableRobot aRobot, int aWidth, int aHeight) {
        myPenMovement = new PenMovement(this, aRobot);
        myTurtleBox = aTurtleBox;
        myWidth = aWidth;
        myHeight = aHeight;
        myRobot = aRobot;
        myImage = aImage;
        myRobot.registerObserver(this);
    }

    @Override
    public void update () {
        checkVisibility();
        updateTurtlePosition();
        updateTurtleRotation();
    }

    private void updateTurtleRotation () {
        myImage.setRotate(-myRobot.getRotation());
    }

    private void updateTurtlePosition () {
        moveTurtleX();
        moveTurtleY();
        if (myRobot.getPenInformation().isPenUp()) {
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

    double translatedXCoordinate () {
        return myRobot.getCoordinate().getX() + myWidth / 2;
    }

    double translateXCoordinate(double aX) {
    	return aX + myWidth / 2;
    }
    
    double translatedYCoordinate () {
        return -myRobot.getCoordinate().getY() + myHeight / 2;
    }
    
    double translateYCoordinate(double aY){
    	return - aY + myHeight / 2;
    }

    private void moveTurtleX () {
        myImage.setX(translatedXCoordinate());
    }

    private void moveTurtleY () {
        myImage.setY(translatedYCoordinate());
    }

}
