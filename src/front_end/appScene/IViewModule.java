package front_end.appScene;

import javafx.scene.Node;

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
