package front_end.view_modules.variableViewer;

import back_end.model.states.IViewableVariableState;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;


/**
 * Heavily References: http://docs.oracle.com/javafx/2/ui_controls/table-view.htm
 * 
 * @see IVariableViewer
 * This class creates the concrete implementation of the variable viewer. It implements the
 * variable viewer interface and sets the specifics for how the variable viewer will be laid out.
 * 
 * @author George Bernard
 * @author Kayla Schulz
 */
class ConcreteVariableViewer implements IVariableViewer {

    private Pane myVariableViewer;
    private IViewableVariableState myVariableState;

    private TableView<Variable> myVariableTable;
    private TableColumn<Variable, String> myNameColumn;
    private TableColumn<Variable, String> myValueColumn;

    private ObservableList<Variable> myVariables;

    /**
     * Package specific constructor for Variable Viewer
     * 
     * @see IVariableViewer
     * @param width
     * @param height
     */
    @SuppressWarnings("unchecked")
	ConcreteVariableViewer (int width, int height) {
        myVariables = FXCollections.observableArrayList();
        myVariableTable = new TableView<>();
        myVariableTable.setPrefSize(width, height);
        System.out.println("w: " + width + " H: " + height);
        myVariableViewer = new Pane();
        myVariableTable.setEditable(true);
        myNameColumn = new TableColumn<>("Name");
        myNameColumn
                .setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Variable, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call (CellDataFeatures<Variable, String> param) {
                        // TODO Auto-generated method stub
                        return param.getValue().getNameProperty();
                    }

                });

        myValueColumn = new TableColumn<>("Variable");
        myValueColumn
                .setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Variable, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call (CellDataFeatures<Variable, String> param) {
                        // TODO Auto-generated method stub
                        return param.getValue().getValueProperty();
                    }

                });

        myVariableTable.getColumns().addAll(myNameColumn, myValueColumn);
        myVariableTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        myVariableTable.setItems(myVariables);
        
        myVariableViewer.getChildren().add(myVariableTable);
    }

    @Override
    public Node getInstanceAsNode () {
        return myVariableViewer;
    }

    @Override
    public void reset () {
        myVariables.clear();
    }


	@Override
	public void update() {
		myVariables.clear();
		for (String varName : myVariableState.getVariableKeySet()) {
            myVariables.add(new Variable(varName, myVariableState.getValue(varName)));
        }

        myVariableTable.setItems(myVariables);
	}

	@Override
	public void giveVariables(IViewableVariableState aViewVariableState) {
		myVariableState = aViewVariableState;
		myVariableState.registerObserver(this);
	}


}
