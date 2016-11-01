package front_end.view_modules.variableViewer;

import front_end.acceptor.IVariableAcceptor;
import front_end.view_modules.IViewModule;
import javafx.scene.Node;


public interface IVariableViewer extends IViewModule, IVariableAcceptor {

    /**
     * 
     * @return instance as a JavaFX Node
     */
    public Node getInstanceAsNode ();

}
