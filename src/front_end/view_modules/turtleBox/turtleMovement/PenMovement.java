package front_end.view_modules.turtleBox.turtleMovement;

import front_end.view_modules.turtleBox.ITurtleBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class PenMovement implements ITurtleMovement {

    // TODO: What I need from back-end: previous coordinates to draw the line
    // In this case: curX and curY
    private GraphicsContext gc;

    @Override
    public void updateTurtle () {
        // TODO Auto-generated method stub
        //Need to get TurtleBox
        //gc.setStroke(Color.BLACK);
        //gc.setLineWidth(5);
        //gc.strokeLine(curX, curY, translatedXCoordinate(), translatedYCoordinate());
    }
    
    public void drawWithPen(ITurtleBox myTurtleBox) {
        //TODO: Fix turtleBox being null
        gc = myTurtleBox.getGC();
    }

}
