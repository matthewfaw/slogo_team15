package front_end.applicationController;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import back_end.controller.ModelController;
import back_end.model.exception.EmptyInputException;
import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.exception.UnexpectedCommandException;
import front_end.appScene.ApplicationScene;
import front_end.view_modules.errorViewer.IErrorViewer;
import front_end.view_modules.helpPage.HelpPage;
import front_end.view_modules.image_color_module.interfaces.IImageColorModule;
import front_end.view_modules.penProperties.IPenPopup;
import front_end.view_modules.penProperties.PenPopup;
import front_end.view_modules.scriptViewer.IScriptViewer;
import front_end.view_modules.textEditor.ITextEditor;
import front_end.view_modules.toolbar.IToolbar;
import front_end.view_modules.turtleBox.ITurtleBox;
import front_end.view_modules.turtlestate.IAllRobotsStateBox;
import front_end.view_modules.variableViewer.IVariableViewer;
import integration.languages.Languages;
import integration.router.IRouter;
import integration.router.RouterFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * 
 * @author Kayla Schulz
 * @author George Bernard
 *
 */
public class ApplicationController {

    private ModelController myModel;
    private ApplicationScene myAppScene;
    private HelpPage myHelpPage;
    private Group myRoot;

    private IRouter myRobotRouter;
    
    /** View Modules **/
    private IToolbar myToolbar;
    private ITextEditor myTextEditor;
    private IErrorViewer myErrorViewer;
    private IVariableViewer myVariableViewer;
    private ITurtleBox myTurtleBox;
    private IScriptViewer myScriptViewer;
    private IImageColorModule myShapeColorModule;
    private IAllRobotsStateBox myStatesBox;
    private IPenPopup myPenPopup;
    
    
    private String TITLE = "SLOGO";

    public ApplicationController (int aWidth, int aHeight) {
        myAppScene = new ApplicationScene(aWidth, aHeight);
        myRobotRouter = RouterFactory.build(myAppScene);
        myModel = new ModelController(myRobotRouter);
       
        getFromScene();
        configureToolbar();
    }

    private void getFromScene () {
        myToolbar = myAppScene.getMyToolbar();
        myTextEditor = myAppScene.getMyTextEditor();
        myErrorViewer = myAppScene.getMyErrorViewer();
        myVariableViewer = myAppScene.getMyVariableViewer();
        myHelpPage = myAppScene.getMyHelpPage();
        myRoot = myAppScene.getMyRoot();
        myTurtleBox = myAppScene.getMyTurtleBox();
        myScriptViewer = myAppScene.getMyScriptViewer();
        myShapeColorModule = myAppScene.getMyShapeColorModule();
        myStatesBox = myAppScene.getMyStatesBox();
        // TODO: Change this to interface - Kayla
        myPenPopup = new PenPopup();
    }

    public Scene getScene () {
        return myAppScene.getScene();
    }

    private void runAll () {
        StringBuilder sb = new StringBuilder();

        String newLine = "\n";

        for (int i = 0; i < myTextEditor.getInstructionList().size(); i++) {
            sb.append(myTextEditor.getInstructionList().get(i));
            sb.append(newLine);
        }

        try {
            myModel.userInputToModel(sb.toString());
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException
                | UnexpectedCharacterException
                | UnexpectedCommandException | EmptyInputException e) {
            myErrorViewer.giveErrorStructure(e);
        }

        myVariableViewer.showVariables(myModel.getVariableMap());
    }

    private void loadHelp () {
        myHelpPage.loadHelpPage();
        // TODO: Kayla - Change this to reflect a back button or new scene
        myRoot.getChildren().add(myHelpPage.getMyView());
    }

    private void resetAll () {
        myTextEditor.reset();
        myErrorViewer.reset();
        myTurtleBox.reset();
        myVariableViewer.reset();
        myScriptViewer.reset();
        myShapeColorModule.reset();
        myStatesBox.reset();
    }

    private Map<Languages, EventHandler<ActionEvent>> makeLanguageMap () {
        Map<Languages, EventHandler<ActionEvent>> languageMap = new HashMap<>();

        Collection<Languages> langSet = Arrays.asList(Languages.values());

        for (Languages lang : langSet) {
            languageMap.put(lang, e -> {
                myModel.setLanguage(lang);
                myToolbar.switchLanguage(lang);
            });
        }

        return languageMap;
    }

    private void configureToolbar () {
        myToolbar.onResetPress(e -> resetAll());

        myToolbar.onRunPress(e -> runAll());

        myToolbar.onHelpPress(e -> loadHelp());

        myToolbar.onLanguageSelect(makeLanguageMap());

        myToolbar.onPenPress(e -> popupPenSelector());

        myToolbar.onBuildPress(e -> buildCommands());

    }

    private void configurePenPopup (Stage stage) {
        myPenPopup.onApplyPress(e -> collectPenInfo(stage));
        myPenPopup.onClearPress(e -> clearPenSettings());
    }

    private void clearPenSettings () {
        myPenPopup.clear();
    }

    private void collectPenInfo (Stage stage) {
        stage.hide();
    }

    private void buildCommands () {
        // TODO: Actually make this method
    }

    private void popupPenSelector () {
        Stage stage = new Stage();
        myPenPopup.initPopup();
        Scene myScene = myPenPopup.getScene();
        stage.setScene(myScene);
        stage.show();
        configurePenPopup(stage);
    }

    /**
     * Returns the title of the SLOGO project
     * 
     * @return Title of project
     */
    public String getTitle () {
        return TITLE;
    }

}
