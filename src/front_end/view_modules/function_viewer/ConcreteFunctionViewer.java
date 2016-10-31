package front_end.view_modules.function_viewer;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


class ConcreteFunctionViewer implements IFunctionViewer {

    private Pane myScriptViewer;
    private final int SPACING = 10;
    private Label myFirstFunction;

    ConcreteFunctionViewer (int aWidth, int aHeight) {
        myScriptViewer = new Pane();
        myScriptViewer.setPrefSize(aWidth, aHeight);
        VBox myFunctionList = new VBox(SPACING);
        HBox myBox = new HBox(SPACING);
        Button myFirstButton = new Button("Press me");
        myFirstFunction = new Label("cats");
        myBox.getChildren().addAll(myFirstFunction, myFirstButton);
        myFunctionList.getChildren().add(myBox);
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
	public void giveFunction(String myFunction) {
	    myFirstFunction.setText(myFunction.trim());
	}

}
