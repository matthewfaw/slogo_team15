package front_end.view_modules.function_viewer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;


class ConcreteFunctionViewer implements IFunctionViewer {

    private Pane myScriptViewer;

    ConcreteFunctionViewer (int aWidth, int aHeight) {
        myScriptViewer = new Pane();
        myScriptViewer.setPrefSize(aWidth, aHeight);
        myScriptViewer.setMaxSize(aWidth, aHeight);
        ListView<String> myFunctionList = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList(
                                                                         "Function 1",
                                                                         "Function 2");
        myFunctionList.setItems(items);
        myScriptViewer.getChildren().add(myFunctionList);
    }

    @Override
    public void reset () {
        // TODO Auto-generated method stub

    }

    @Override
    public Node getInstanceAsNode () {
        // TODO Auto-generated method stub
        return myScriptViewer;
    }

    @Override
    public void switchLanguage (Languages aLanguage) {
        // TODO Auto-generated method stub

    }

	@Override
	public void giveFunction() {
		// TODO Auto-generated method stub
		
	}

}
