package front_end.view_modules.variableViewer;

import back_end.model.states.IViewVariableState;
import front_end.view_modules.ILanguageSwitcher;
import front_end.view_modules.IViewModule;
import javafx.scene.Node;


public interface IVariableViewer extends IViewModule, ILanguageSwitcher {

    /**
     * 
     * @return instance as a JavaFX Node
     */
    public Node getInstanceAsNode ();

    /**
     * Shows variables inside of variable box
     * 
     * @param varMap
     */
    public void showVariables (IViewVariableState aVariableState);

}
