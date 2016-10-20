package appScene.toolbar;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import javafx.event.EventHandler;

import javafx.geometry.Insets;


class ConcreteToolbar implements IToolbar {

    private HBox myToolbar;
    private Button myRun;
    private Button myHelp;
    private Button myBuild;
    private MenuButton myLanguage;
    private Button myStep;
    private Button myReset;

    private static final int SPACING = 5;

    ConcreteToolbar (int aWidth, int aHeight) {
        myToolbar = new HBox(SPACING);
        myToolbar.setPrefSize(aWidth, aHeight);
        myToolbar.setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
        myToolbar.setStyle("-fx-background-color: #336699;");
        myRun = new Button("Run");
        myHelp = new Button("Help");
        myBuild = new Button("Build");
        myLanguage = initLanguageButton();
        myStep = new Button("Step");
        myReset = new Button("Reset");
        myToolbar.getChildren().addAll(myRun, myHelp, myStep, myBuild, myReset, myLanguage);
    }

    @Override
    public void onBuildPress (EventHandler<MouseEvent> event) {
        myBuild.setOnMouseClicked(event);
    }

    @Override
    public void onRunPress (EventHandler<MouseEvent> event) {
        myRun.setOnMouseClicked(event);

    }

    @Override
    public void onStepPress (EventHandler<MouseEvent> event) {
        myStep.setOnMouseClicked(event);
    }

    @Override
    public void onHelpPress (EventHandler<MouseEvent> event) {
    	myHelp.setOnMouseClicked(event);
    }

    @Override
    public void onLanguagePress (EventHandler<MouseEvent> event) {
    	// TODO Make into onLanguageSelect, AND implement this
    }

    @Override
    public void onResetPress (EventHandler<MouseEvent> event) {
        myReset.setOnMouseClicked(event);

    }

    @Override
    public void reset () {
        // TODO Auto-generated method stub

    }

    @Override
    public Node getInstanceAsNode () {
        return myToolbar;
    }

    /************ Private Helper Methods ************/
        
    private MenuButton initLanguageButton(){
    	MenuButton languageSelection = new MenuButton("Languages");
    	
    	MenuItem language1 = new MenuItem("English");
    	MenuItem language2 = new MenuItem("French");
    	MenuItem language3 = new MenuItem("Spanish");
    	
    	languageSelection.getItems().addAll(language1, language2, language3);
    	
    	return languageSelection;
    }
    
}
