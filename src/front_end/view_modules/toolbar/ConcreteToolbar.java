
package front_end.view_modules.toolbar;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

class ConcreteToolbar implements IToolbar {

    private HBox myToolbar;
    private Button myRun;
    private Button myHelp;
    private Button myBuild;
    private MenuButton myLanguage;
    private Button myStepInstr;
    private Button myStepLine;
    private Button myReset;
    private Button myPenFunctions;
    private ResourceBundle myGUIResources;

    private boolean myIsBuilt;
    
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
        myToolbar.getChildren().addAll(myRun, myStepLine, myBuild, myStepInstr, myHelp, myPenFunctions, myReset, myLanguage);

        myIsBuilt = false;
    }
    
    private void setButtonText () {
        myRun = new Button(myGUIResources.getString("RunButton"));
        myHelp = new Button(myGUIResources.getString("HelpButton"));
        myBuild = new Button(myGUIResources.getString("BuildButton"));
        myLanguage = new MenuButton(myGUIResources.getString("LanguageDropDown") + ": " + Languages.DEFAULT.getName());
        myStepInstr = new Button(myGUIResources.getString("StepInstrButton"));
        myStepLine = new Button(myGUIResources.getString("StepLineButton"));
        myReset = new Button(myGUIResources.getString("ClearButton"));
        myPenFunctions = new Button(myGUIResources.getString("PenButton"));
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
    public void onStepInstrPress (EventHandler<MouseEvent> event) {
        myStepInstr.setOnMouseClicked(event);
    }
    
	@Override
	public void onStepLinePress(EventHandler<MouseEvent> aEvent) {
		myStepLine.setOnMouseClicked(aEvent);
	}


    @Override
    public void onHelpPress (EventHandler<MouseEvent> event) {
        myHelp.setOnMouseClicked(event);
    }

    @Override
    public void onLanguageSelect (Map<Languages, EventHandler<ActionEvent>> aLanguageEventMap) {

        for (Languages lang : aLanguageEventMap.keySet()) {
            MenuItem languageItem = new MenuItem(lang.getName());
            languageItem.setOnAction(aLanguageEventMap.get(lang));
            myLanguage.getItems().add(languageItem);
        }

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

    @Override
    public void switchLanguage (Languages aLanguage) {
        myLanguage
                .setText(myGUIResources.getString("LanguageDropDown") + ": " + aLanguage.getName());
    }

    @Override
    public void onPenPress (EventHandler<MouseEvent> aEvent) {
        myPenFunctions.setOnMouseClicked(aEvent);
    }

	@Override
	public Button getButton(ButtonTypes aButton) {
		switch (aButton) {
		case BUILD: 			return myBuild;
		case RUN: 				return myRun;
		case STEP_INSTRUCTION:	return myStepInstr;
		case STEP_LINE:			return myStepLine;
		case HELP:				return myHelp;	
		case RESET:				return myReset;
		case PEN:				return myPenFunctions;
		default:				return null;
		}
	}

}
