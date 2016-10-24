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
    //TODO: Reset these values
    private double curX = 500/2;
    private double curY = 500/2;
    
    public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
    public void gameLoop() {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> updateTurtle());
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
    
    public void updateTurtle() {
        gc = ConcreteTurtleBox.getGC();
        myTurtle = ConcreteTurtleBox.getTurtle();
        if(curX != myRobot.getX()) {
            moveTurtleX(1);
            if(myRobot.isPenDown()) {
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(5);
                gc.strokeLine(curX, curX+1, myRobot.getY(), myRobot.getY());
            }
        }
    }
    
    private void moveTurtleX(int i) {
        myTurtle.setX(curX+i);
        curX = myRobot.getX();
    }
    
}
