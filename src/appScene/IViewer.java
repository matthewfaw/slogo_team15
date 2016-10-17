package appScene;

import javafx.scene.Node;

public interface IViewer {
    
    /**
     * Clear the text editor to its original, empty state
     */
    public void clear();
    
    
    public Node getInstanceAsNode();
    
}
