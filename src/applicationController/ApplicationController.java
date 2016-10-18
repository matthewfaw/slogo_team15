package applicationController;

import appScene.errorViewer.ConcreteErrorViewer;
import appScene.scriptViewer.ConcreteScriptViewer;
import appScene.textEditor.ConcreteTextEditor;
import appScene.toolbar.ConcreteToolbar;
import appScene.turtleBox.ConcreteTurtleBox;
import appScene.variableViewer.ConcreteVariableViewer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class ApplicationController {

    private GridPane myApplicationView;
    private Scene myScene;
    private Group myRoot;
    private String TITLE = "SLOGO";
    private ConcreteToolbar myToolbar;
    private ConcreteTextEditor myTextEditor;
    private ConcreteErrorViewer myErrorViewer;
    private ConcreteTurtleBox myTurtleBox;
    private ConcreteVariableViewer myVariableViewer;
    private ConcreteScriptViewer myScriptViewer;

    public ApplicationController () {
        myApplicationView = new GridPane();
    }

    public Scene init (int width, int height, int size) {
        myToolbar = new ConcreteToolbar(width, height / 20);
        myTextEditor = new ConcreteTextEditor(width / 4, height);
        myErrorViewer = new ConcreteErrorViewer(size / 2, height / 2);
        myTurtleBox = new ConcreteTurtleBox(size, size);
        myVariableViewer = new ConcreteVariableViewer(size / 2, size / 2);
        myScriptViewer = new ConcreteScriptViewer(size/2, size/2); // double check these values

        myRoot = new Group();
        myRoot.getChildren().addAll(myApplicationView);
        myScene = new Scene(myRoot, width, height, Color.WHITE);
        /*
         * myApplicationView.add( myToolbar.getInstanceAsNode(), 0, 0, GridPane.REMAINING, 1);
         * myApplicationView.add( myTextEditor.getInstanceAsNode(), 0, 2, 1, 2);
         * myApplicationView.add( myTurtleBox.getInstanceAsNode(), 0, 1, 2, 1);
         * myApplicationView.add( myErrorViewer.getInstanceAsNode(), 1, 1, 1, 1);
         * myApplicationView.add(myVariableViewer.getInstanceAsNode(), 3, 2, 1, 1);
         */
        myApplicationView.add(myToolbar.getInstanceAsNode(), 0, 0, GridPane.REMAINING, 1);
        myApplicationView.add(myTextEditor.getInstanceAsNode(), 0, 1, 1, 2);
        myApplicationView.add(myTurtleBox.getInstanceAsNode(), 1, 1, 2, 2);
        myApplicationView.add(myErrorViewer.getInstanceAsNode(), 3, 1, 1, 1);
        myApplicationView.add(myVariableViewer.getInstanceAsNode(), 3, 2);

        return myScene;
    }

    public String getTitle () {
        return TITLE;
    }
}
