package front_end.appScene;

import integration.languages.Languages;
import javafx.scene.Node;

public interface IViewer {
    
    /**
     * Resets the viewer to the initial state, 
     * equivalent to a new instance from the constructor
     */
    public void reset();
    
    
    /**
     * Returns the viewer module, but as a node
     * 
     * @return JavaFX node that corresponds to this Viewer Module
     */
    public Node getInstanceAsNode();
    
    
    public void switchLanguage(Languages aLanguage);
}
