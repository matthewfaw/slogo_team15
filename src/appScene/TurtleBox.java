package appScene;

import javafx.scene.Group;
import javafx.scene.shape.Path;

public class TurtleBox {

    /**
     * Returns the root with all components of turtle box
     * @return root of the turtlebox
     */
    public Group getRoot() {
        return new Group();
    }
    
    /**
     * Display the animation after it has been parsed and interpreted from
     * the back-end
     * @param Path received from the model
     */
    public void displayPath(Path x) {
    }
    
    /**
     * Clears the turtle box to its initial settings
     */
    public void clear() {
        
    }
}
