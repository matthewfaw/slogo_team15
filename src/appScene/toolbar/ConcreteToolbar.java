package appScene.toolbar;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import java.util.ResourceBundle;
import javafx.event.EventHandler;

import javafx.geometry.Insets;


class ConcreteToolbar implements IToolbar {

    private HBox myToolbar;
    private Button myRun;
    private Button myHelp;
    private Button myBuild;
    private MenuButton myLanguage;
    private Button myStep;
    private Button myClear;
    private ResourceBundle myGUIResources;

    private static final int SPACING = 5;

    ConcreteToolbar (int aWidth, int aHeight) {
        String initFile = "resources.frontend";
        String fileName = "/EnglishToolbar";
        myGUIResources = ResourceBundle.getBundle(initFile + fileName);
        setButtonText();
        myToolbar = new HBox(SPACING);
        myToolbar.setPrefSize(aWidth, aHeight);
        myToolbar.setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
        myToolbar.setStyle("-fx-background-color: #336699;");
        myToolbar.getChildren().addAll(myRun, myHelp, myStep, myBuild, myClear, myLanguage);

    }
    
    private void setButtonText() {
        myRun = new Button(myGUIResources.getString("RunButton"));
        myHelp = new Button(myGUIResources.getString("HelpButton"));
        myBuild = new Button(myGUIResources.getString("BuildButton"));
        myLanguage = initLanguageButton();
        myStep = new Button(myGUIResources.getString("StepButton"));
        myClear = new Button(myGUIResources.getString("ClearButton"));
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

    private MenuButton initLanguageButton(){
    	MenuButton languageSelection = new MenuButton(myGUIResources.getString("LanguageDropDown"));
    	
    	MenuItem language1 = new MenuItem("English");
    	MenuItem language2 = new MenuItem("French");
    	MenuItem language3 = new MenuItem("Spanish");
    	
    	languageSelection.getItems().addAll(language1, language2, language3);
    	
    	return languageSelection;
    }
    
}
