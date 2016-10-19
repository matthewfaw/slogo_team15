package appScene.scriptViewer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

class ConcreteScriptViewer implements IScriptViewer {

    private Pane myScriptViewer;
    
    ConcreteScriptViewer (int aWidth, int aHeight) {
        myScriptViewer = new Pane();
        myScriptViewer.setPrefSize(aWidth, aHeight);
        ListView<String> myFunctionList = new ListView<String>();
        ObservableList<String> items =FXCollections.observableArrayList (
                                                                         "Function 1", "Function 2");
        myFunctionList.setItems(items);
        myScriptViewer.getChildren().add(myFunctionList);
    }

    @Override
    public void clear () {
        // TODO Auto-generated method stub

    }

    @Override
    public void giveScriptStructure () {
        // TODO Auto-generated method stub

    }

    @Override
    public void onScriptPress () {
        // TODO Auto-generated method stub

    }

    @Override
    public Node getInstanceAsNode () {
        // TODO Auto-generated method stub
        return myScriptViewer;
    }

}
