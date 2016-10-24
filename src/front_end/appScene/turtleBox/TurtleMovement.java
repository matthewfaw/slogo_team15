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
    private double curX = myWidth/2;
    private double curY = myHeight/2;
    
    public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private ConcreteTurtleBox myConTurtBox;
    
    public TurtleMovement(ConcreteTurtleBox myConTurt, int width, int height) {
        myConTurtBox=myConTurt;
        myWidth = width;
        myHeight = height;
    }
    public void gameLoop() {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> updateTurtle());
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
    
    public void updateTurtle() {
        gc = myConTurtBox.getGC();
        myTurtle = myConTurtBox.getTurtle();
        myRobot = myConTurtBox.getRobot();
        
        System.out.println("CurX: " + curX);
        System.out.println("CurY: " + curY);
        System.out.println("RobotX: " + myRobot.getX());
        System.out.println("RobotY: " + myRobot.getY());

        if(curX != myRobot.getX()) {
            System.out.println("HERE");
            moveTurtleX();
            if(myRobot.isPenDown()) {
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(5);
                gc.strokeLine(curX, curX+1, myRobot.getY(), myRobot.getY());
            }
        }
        if(curY != myRobot.getY()) {
            System.out.println("HERE");
            moveTurtleY();
            if(myRobot.isPenDown()) {
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(5);
                gc.strokeLine(myRobot.getX(), myRobot.getX(), curY, curY+1);
            }
        }
        
    }
    
    private void moveTurtleX() {
        myTurtle.setX(myRobot.getX());
        curX = myRobot.getX();
    }
    
    private void moveTurtleY() {
        myTurtle.setY(myRobot.getY());
        curY = myRobot.getY();
    }
    
}
