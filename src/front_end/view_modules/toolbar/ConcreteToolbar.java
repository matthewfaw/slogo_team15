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
import integration.languages.Languages;


class ConcreteToolbar implements IToolbar {

    private HBox myToolbar;
    private Button myRun;
    private Button myHelp;
    private Button myBuild;
    private MenuButton myLanguage;
    private Button myStep;
    private Button myReset;
    private Button myPenFunctions;
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
        myToolbar.getChildren().addAll(myRun, myHelp, myStep, myBuild, myPenFunctions, myReset, myLanguage);

    }

    private void setButtonText () {
        myRun = new Button(myGUIResources.getString("RunButton"));
        myHelp = new Button(myGUIResources.getString("HelpButton"));
        myBuild = new Button(myGUIResources.getString("BuildButton"));
        myLanguage =
                new MenuButton(myGUIResources.getString("LanguageDropDown") + ": " +
                               Languages.DEFAULT.getName());
        myStep = new Button(myGUIResources.getString("StepButton"));
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
    public void onStepPress (EventHandler<MouseEvent> event) {
        myStep.setOnMouseClicked(event);
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
        // TODO Auto-generated method stub
        myPenFunctions.setOnMouseClicked(aEvent);
    }

}
