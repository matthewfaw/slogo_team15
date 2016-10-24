package front_end.appScene.variableViewer;

import front_end.appScene.IViewer;
import javafx.scene.Node;
import model.states.IViewVariableState;

public interface IVariableViewer extends IViewer {

    /**
     * Clear the variable box to its original, empty state
     */
    public void clear();
    
    /**
     * 
     * @return instance as a JavaFX Node
     */
    public Node getInstanceAsNode();
    
    /**
     * Shows variables inside of variable box
     * @param varMap
     */
    public void showVariables(IViewVariableState aVariableState);
    
}
