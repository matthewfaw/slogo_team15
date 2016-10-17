package appScene;

import javafx.scene.Group;

public interface ITurtleBox {

    /**
     * Returns the root with all components of turtle box
     * @return root of the turtlebox
     */
    public Group getRoot();
    
    /**
     * Draw the moves from robot over all turtles
     * @param Robot from back-end (this will later be of type robot)
     */
    public void addRobot(Object robot);
    
    /**
     * Clears the turtle box to its initial settings
     */
    public void clear();
}
