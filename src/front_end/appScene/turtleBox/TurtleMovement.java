package front_end.appScene.turtleBox;

import back_end.model.robot.IViewRobot;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class TurtleMovement implements ITurtleMovement {

    IViewRobot myRobot;
    //TODO: Get rid of magic numbers here
    ConcreteTurtleBox myConcreteTurtleBox = new ConcreteTurtleBox(500, 500);
    private GraphicsContext gc;
    private ImageView myTurtle;
    private double curX = myRobot.getX();
    private double curY = myRobot.getY();
    
    public void updateTurtle() {
        gc = myConcreteTurtleBox.getGC();
        myTurtle = myConcreteTurtleBox.getTurtle();
        if(myRobot.isPenDown() && curX != myRobot.getX()) {
            for(int i = 0; i < Math.abs(curX-myRobot.getX()); i++) {
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(5);
                gc.strokeLine(curX, curX+i, myRobot.getY(), myRobot.getY());
                myTurtle.setX(curX+i);
            }
        }
    }
    
}
