package front_end.view_modules;

import javafx.scene.Node;

/**
 * Specifies the view module property. 
 * Every front end feature implements this.
 * 
 * @author George Bernard
 */
public interface IViewModule {

    /**
     * Resets the viewer to the initial state,
     * equivalent to a new instance from the constructor
     */
    public void reset ();

    /**
     * Returns the viewer module, but as a node
     * 
     * @return JavaFX node that corresponds to this Viewer Module
     */
    public Node getInstanceAsNode ();

}
