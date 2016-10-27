package front_end.appScene.turtleBox.turtleMovement;

import back_end.model.robot.IViewRobot;
import front_end.appScene.turtleBox.ITurtleBox;
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
    // TODO: Change this concrete turtle box to turtle box interface
    private ITurtleBox myConTurtBox;

    public TurtleMovement (ITurtleBox myConTurt, int width, int height) {
        myConTurtBox = myConTurt;
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
        gc = myConTurtBox.getGC();
        myTurtle = myConTurtBox.getTurtle();
        myRobot = myConTurtBox.getRobot();

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
            myConTurtBox.removeTurtle();
        }
        else {
            myConTurtBox.showTurtle();
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
