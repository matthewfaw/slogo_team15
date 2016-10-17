package appScene.variableViewer;

import java.util.Map;
import javafx.scene.Node;

public interface IVariableViewer {

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
    public void showVariables(Map<String, ? extends Number> varMap);
    
}
