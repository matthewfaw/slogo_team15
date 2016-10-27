package front_end.appScene.variableViewer;

import back_end.model.states.IViewVariableState;
import front_end.appScene.IViewer;
import javafx.scene.Node;


public interface IVariableViewer extends IViewer {

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
