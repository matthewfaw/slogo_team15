package appScene.toolbar;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;
import javafx.geometry.Insets;


public class ConcreteToolbar implements IToolbar {

    private HBox myToolbar;
    private Button myRun;
    private Button myHelp;
    private Button myBuild;
    private Button myLanguage;
    private Button myStep;
    private Button myClear;

    private static final int SPACING = 5;

    public ConcreteToolbar (int width, int height) {
        myToolbar = new HBox(SPACING);
        myToolbar.setPrefSize(width, height);
        myToolbar.setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
        myToolbar.setStyle("-fx-background-color: #336699;");
        myRun = new Button("Run");
        myHelp = new Button("Help");
        myBuild = new Button("Build");
        myLanguage = new Button("Language");
        myStep = new Button("Step");
        myClear = new Button("Clear");
        myToolbar.getChildren().addAll(myRun, myHelp, myStep, myBuild, myClear, myLanguage);
    }

    @Override
    public void onCompilePress (EventHandler<MouseEvent> event) {
        // TODO Auto-generated method stub
        myBuild.setOnMouseClicked(event);

    }

    @Override
    public void onRunPress (EventHandler<MouseEvent> event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStepPress (EventHandler<MouseEvent> event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onHelpPress (EventHandler<MouseEvent> event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLanguagePress (EventHandler<MouseEvent> event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClearPress (EventHandler<MouseEvent> event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void clear () {
        // TODO Auto-generated method stub

    }

    @Override
    public Node getInstanceAsNode () {
        return myToolbar;
    }

}
