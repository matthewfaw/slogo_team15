package front_end.view_modules.turtleBox.turtleMovement;

import back_end.model.robot.IViewableRobot;
import front_end.view_modules.turtleBox.ITurtleBox;
import integration.observe.IObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class PenMovement {

    private TurtleMovement myMovement;
    private IViewableRobot myRobot;
    private GraphicsContext gc;
    
    public PenMovement(TurtleMovement myTurtMove, IViewableRobot aRobot) {
    	myRobot = aRobot;
        myMovement = myTurtMove;
    }
    
    protected void drawWithPen(ITurtleBox myTurtleBox) {
        //TODO: Fix turtleBox being null
        gc = myTurtleBox.getGC();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.strokeLine(	myMovement.translateXCoordinate(myRobot.getPreviousCoordinate().getX()), 
  			  			myMovement.translateYCoordinate(myRobot.getPreviousCoordinate().getY()), 
  			  			myMovement.translateXCoordinate(myRobot.getCurrentCoordinate().getX()), 
			  			myMovement.translateYCoordinate(myRobot.getCurrentCoordinate().getY()));
    }

}
