package front_end.view_modules.variableViewer;

import back_end.model.states.IViewVariableState;
import integration.languages.Languages;
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
 * @author George Bernard
 * @author Kayla Schulz
 *
 *         Heavily References: http://docs.oracle.com/javafx/2/ui_controls/table-view.htm
 *
 */
class ConcreteVariableViewer implements IVariableViewer {

    private Pane myVariableViewer;

    private TableView<Variable> myVariableTable;
    private TableColumn<Variable, String> myNameColumn;
    private TableColumn<Variable, String> myValueColumn;

    private ObservableList<Variable> myVariables;

    ConcreteVariableViewer (int width, int height) {
        myVariables = FXCollections.observableArrayList(
                                                        new Variable("this is hardcoded", 7),
                                                        new Variable("wow, also hardcoded", 1000.8),
                                                        new Variable("much hardcoded", 9));

        myVariableTable = new TableView<>();
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
    public void showVariables (IViewVariableState aVariableState) {
        myVariables.clear();

        for (String varName : aVariableState.getVariableKeySet()) {
            myVariables.add(new Variable(varName, aVariableState.getValue(varName)));
        }

        myVariableTable.setItems(myVariables);
    }

    @Override
    public void switchLanguage (Languages aLanguage) {
        // TODO Auto-generated method stub

    }

}
