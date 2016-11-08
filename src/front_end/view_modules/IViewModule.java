package front_end.view_modules;

import javafx.scene.Node;

/**
 * Specifies the view module property. 
 * Every front end feature implements this.
 * 
 * DESIGN CHOICE:
 * While the classes that implement this interface have a lot of duplicated code, 
 * using this interface instead is a better decision. The pane should be decided by 
 * the module itself. In many cases this was a scroll pane, but inheritance would likely
 * have required overriding private methods which would require in the end more code to 
 * maintain the flexibility of composition over inheritance.
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
