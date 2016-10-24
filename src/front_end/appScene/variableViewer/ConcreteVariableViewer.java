package front_end.appScene.variableViewer;

import java.util.Map;

import back_end.model.states.IViewVariableState;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

class ConcreteVariableViewer implements IVariableViewer {

    private Pane myVariableViewer;
    private TableView myVariableTable;
    private TableColumn myNameColumn;
    private TableColumn myValueColumn;
   
    ConcreteVariableViewer(int width, int height) {
        myVariableTable = new TableView<>();
        myVariableViewer = new Pane();
        myVariableTable.setEditable(true);
        myNameColumn = new TableColumn("Name");
        myValueColumn = new TableColumn("Variable");
        myVariableTable.getColumns().addAll(myNameColumn, myValueColumn);
        myVariableTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        myVariableViewer.getChildren().add(myVariableTable);
    }
    
    @Override
    public void clear () {
        // TODO Auto-generated method stub

    }

    @Override
    public Node getInstanceAsNode () {
        return myVariableViewer;
    }

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showVariables(IViewVariableState aVariableState) {
		
		for (String varName : aVariableState.getVariableKeySet()) {
		}
		
		
	}

}
