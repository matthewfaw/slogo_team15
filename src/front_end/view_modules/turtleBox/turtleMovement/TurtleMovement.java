package front_end.view_modules.turtleBox.turtleMovement;

import back_end.model.robot.IViewRobot;
import front_end.view_modules.turtleBox.ITurtleBox;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class TurtleMovement implements ITurtleMovement {

    IViewRobot myRobot;
    private GraphicsContext gc;
    private ImageView myTurtle;
    private int myWidth;
    private int myHeight;
    private double curX;
    private double curY;

    public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private ITurtleBox myTurtleBox;

    public TurtleMovement (ITurtleBox myConTurt, int width, int height) {
        myTurtleBox = myConTurt;
        myWidth = width;
        myHeight = height;
        curX = myWidth / 2;
        curY = myHeight / 2;
    }

    public void gameLoop () {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> updateTurtle());
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    @Override
    public void updateTurtle () {
        gc = myTurtleBox.getGC();
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
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(5);
            gc.strokeLine(curX, curY, translatedXCoordinate(), translatedYCoordinate());
        }
        curX = translatedXCoordinate();
        curY = translatedYCoordinate();
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
