package appScene.variableViewer;

import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class ConcreteVariableViewer implements IVariableViewer {

    private Pane myVariableViewer;
   
    public ConcreteVariableViewer(int width, int height) {
        TableView table = new TableView();
        myVariableViewer = new Pane();
        table.setEditable(true);
        TableColumn nameCol = new TableColumn("Name");
        TableColumn valueCol = new TableColumn("Variable");
        table.getColumns().addAll(nameCol, valueCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        myVariableViewer.getChildren().add(table);
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
