package appScene.variableViewer;

import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ConcreteVariableViewer implements IVariableViewer {

    private BorderPane myVariableViewer;
    
    public ConcreteVariableViewer(int width, int height) {
        myVariableViewer = new BorderPane();
        VBox varNames = new VBox(width/2);
        VBox varValues = new VBox(width/2);
        myVariableViewer.setLeft(varNames);
        myVariableViewer.setRight(varValues);
        TextField curTextField = new TextField();
        varNames.getChildren().add(curTextField);
    }
    
    @Override
    public void clear () {
        // TODO Auto-generated method stub

    }

    @Override
    public void showVariables (Map<String, ? extends Number> varMap) {
        // TODO Auto-generated method stub

    }

    @Override
    public Node getInstanceAsNode () {
        return myVariableViewer;
    }

}
