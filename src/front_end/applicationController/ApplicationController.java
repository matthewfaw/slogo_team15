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
import front_end.view_modules.penProperties.PenPopup;
import front_end.view_modules.scriptViewer.IScriptViewer;
import front_end.view_modules.shape_color_module.interfaces.IShapeColorModule;
import front_end.view_modules.textEditor.ITextEditor;
import front_end.view_modules.toolbar.IToolbar;
import front_end.view_modules.turtleBox.ITurtleBox;
import front_end.view_modules.variableViewer.IVariableViewer;
import integration.languages.Languages;
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
    
    private IToolbar myToolbar;
    private ITextEditor myTextEditor;
    private IErrorViewer myErrorViewer;
    private IVariableViewer myVariableViewer;
    private ITurtleBox myTurtleBox;
    private IScriptViewer myScriptViewer;
    private IShapeColorModule myShapeColorModule;
    private PenPopup myPenPopup;

    private String TITLE = "SLOGO";

    public ApplicationController () {
        myAppScene = new ApplicationScene();
        myModel = new ModelController();
    }
    
    private void initFromScene() {
        myToolbar = myAppScene.getMyToolbar();
        myTextEditor = myAppScene.getMyTextEditor();
        myErrorViewer = myAppScene.getMyErrorViewer();
        myVariableViewer = myAppScene.getMyVariableViewer();
        myHelpPage = myAppScene.getMyHelpPage();
        myRoot = myAppScene.getMyRoot();
        myTurtleBox = myAppScene.getMyTurtleBox();
        myScriptViewer = myAppScene.getMyScriptViewer();
        myShapeColorModule = myAppScene.getMyShapeColorModule();
        //TODO: Change this to interface - Kayla
        myPenPopup = new PenPopup();
    }

    public Scene init (int aWidth, int aHeight) {
        Scene myScene = myAppScene.initScene(aWidth, aHeight);
        initFromScene();
        myModel.giveRobotObservers(myTurtleBox);
        configureToolbar();
        return myScene;
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

    }
    
    private void popupPenSelector() {
       Stage stage = new Stage();
       Scene myScene = myPenPopup.initPopup();
       stage.setScene(myScene);
       stage.show();
    }

    public String getTitle () {
        return TITLE;
    }

}
