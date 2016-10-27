package front_end.appScene.variableViewer;

import back_end.model.states.IViewVariableState;
import front_end.ILanguageSwitcher;
import front_end.appScene.IViewModule;
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
