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
    private double curX = 200;
    private double curY = 200;

    public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private ConcreteTurtleBox myConTurtBox;

    public TurtleMovement (ConcreteTurtleBox myConTurt, int width, int height) {
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
        
        System.out.println("CurX: " + curX);
        System.out.println("CurY: " + curY);
        System.out.println("RobotX: " + myRobot.getX());
        System.out.println("RobotY: " + myRobot.getY());

        if (curX != myRobot.getX()+myWidth/2) {
            moveTurtleX();
            if (myRobot.isPenDown()) {
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(5);
                gc.strokeLine(curX, myRobot.getX()+myHeight/2, myRobot.getY()+myWidth/2, myRobot.getY()+myHeight/2);
            }
            curX = myRobot.getX()+myWidth/2;
            System.out.println("Houston we have a problem");
        }
        if (curY != myRobot.getY()+myHeight/2) {
            moveTurtleY();
            if (myRobot.isPenDown()) {
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(5);
                gc.strokeLine(myRobot.getX()+myWidth/2, curY, myRobot.getX()+myWidth/2, myRobot.getY()+myHeight/2);
                System.out.println("Check: " + myRobot.getX()+myWidth/2);
                System.out.println("Check2: " + myRobot.getX());
            }
            curY = myRobot.getY()+myHeight/2;
            System.out.println("Updated y: " + curY);
        }

    }

    private void moveTurtleX () {
        myTurtle.setX(myRobot.getX()+myWidth/2);
        
        System.out.println("Updated x: " + curX);
    }

    private void moveTurtleY () {
        myTurtle.setY(myRobot.getY()+myHeight/2);
    }
    
    

}
