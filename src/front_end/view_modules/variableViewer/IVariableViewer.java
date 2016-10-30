package front_end.view_modules.variableViewer;

import back_end.model.states.IViewVariableState;
import front_end.acceptor.IVariableAcceptor;
import front_end.view_modules.IViewModule;
import integration.languages.ILanguageSwitcher;
import javafx.scene.Node;


public interface IVariableViewer extends IViewModule, ILanguageSwitcher, IVariableAcceptor {

    /**
     * 
     * @return instance as a JavaFX Node
     */
    public Node getInstanceAsNode ();

}
