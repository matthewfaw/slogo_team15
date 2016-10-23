package appScene.turtleBox;

import appScene.IViewer;

public interface ITurtleBox extends IViewer {

    /**
     * Draw the moves from robot over all turtles
     * @param Robot from back-end (this will later be of type robot)
     */
    public void addRobot(Object robot);
    
}
