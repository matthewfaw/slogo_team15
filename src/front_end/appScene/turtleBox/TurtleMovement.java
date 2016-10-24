package front_end.appScene.turtleBox;

import back_end.model.robot.IViewRobot;
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
    private double curRotate;

    public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private ConcreteTurtleBox myConTurtBox;

    public TurtleMovement (ConcreteTurtleBox myConTurt, int width, int height) {
        curRotate = 0;
        myConTurtBox = myConTurt;
        myWidth = width;
        myHeight = height;
        curX = myWidth/2;
        curY = myHeight/2;
    }

    public void gameLoop () {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> updateTurtle());
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    public void updateTurtle () {
        gc = myConTurtBox.getGC();
        myTurtle = myConTurtBox.getTurtle();
        myRobot = myConTurtBox.getRobot();

        if(!myRobot.isVisible()) {
            myConTurtBox.removeTurtle();
        }
        
        if(myRobot.isVisible()) {
            myConTurtBox.showTurtle();
        }
        
            moveTurtleX();
            if (myRobot.isPenDown()) {
                gc.setStroke(Color.RED);
                gc.setLineWidth(5);
                gc.strokeLine(curX, curY, translateX(), translateY());
            }
            curX = translateX();
            moveTurtleY();
            curY = translateY();

            myTurtle.setRotate(myRobot.getRotation());
            curRotate = myRobot.getRotation();

    }
    
    private double translateX() {
        return myRobot.getX()+myWidth/2;
    }
    
    private double translateY() {
        return -myRobot.getY()+myHeight/2;
    }

    private void moveTurtleX () {
        myTurtle.setX(translateX());
    }

    private void moveTurtleY () {
        myTurtle.setY(translateY());
    }
    
    

}
