package front_end.applicationController;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import back_end.controller.ModelController;
import front_end.appScene.ApplicationScene;
import front_end.view_modules.errorViewer.IErrorViewer;
import front_end.view_modules.function_viewer.IFunctionViewer;
import front_end.view_modules.helpPage.HelpPage;
import front_end.view_modules.history.IHistoryModule;
import front_end.view_modules.image_color_module.interfaces.IImageColorModule;
import front_end.view_modules.penProperties.PenPopup;
import front_end.view_modules.textEditor.ITextEditor;
import front_end.view_modules.toolbar.IToolbar;
import front_end.view_modules.toolbar.IToolbar.ButtonTypes;
import front_end.view_modules.turtleBox.ITurtleBox;
import front_end.view_modules.turtlestate.IAllRobotsStateBox;
import front_end.view_modules.variableViewer.IVariableViewer;
import integration.languages.ILanguageSwitcher.Languages;
import integration.router.IRouter;
import integration.router.RouterFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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
    
    /** View Modules **/
    private IToolbar myToolbar;
    private ITextEditor myTextEditor;
    private IErrorViewer myErrorViewer;
    private IVariableViewer myVariableViewer;
    private ITurtleBox myTurtleBox;
    private IFunctionViewer myScriptViewer;
    private IImageColorModule myImageColorModule;
    private IAllRobotsStateBox myStatesBox;
    private IHistoryModule myHistoryModule;
    private PenPopup myPenPopup;
    
    private int myWidth;
    private int myHeight;
    
    private String TITLE = "SLOGO";
	private int myIndex;

    public ApplicationController (int aWidth, int aHeight) {
        init(aWidth, aHeight);
        myWidth = aWidth;
        myHeight = aHeight;
        myIndex = 0;
    }
    
    private void init(int aWidth, int aHeight) {
        myAppScene = new ApplicationScene(aWidth, aHeight);
        IRouter router = RouterFactory.build(myAppScene);
        myModel = new ModelController(router);
       
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
        myScriptViewer = myAppScene.getMyFunctionViewer();
        myImageColorModule = myAppScene.getMyShapeColorModule();
        myStatesBox = myAppScene.getMyStatesBox();
        myHistoryModule = myAppScene.getMyHistoryModule();
        myPenPopup = new PenPopup();
    }

    public Scene getScene () {
        return myAppScene.getScene();
    }

    private String concatenateInstructions(){
    	StringBuilder sb = new StringBuilder();

        String newLine = "\n";

        for (int i = 0; i < myTextEditor.getInstructionList().size(); i++) {
            sb.append(myTextEditor.getInstructionList().get(i));
            sb.append(newLine);
        }
        return sb.toString();
    }
    
    private void runAll () {
       	myModel.inputAll(concatenateInstructions());
    }

    private void loadHelp () {
    	Stage stage = new Stage();
        myHelpPage.loadHelpPage();
        Scene scene = new Scene(myHelpPage.getMyView());
        stage.setScene(scene);
        stage.show();        
    }

    private void resetAll () {
        myTextEditor.reset();
        myErrorViewer.reset();
        myTurtleBox.reset();
        myVariableViewer.reset();
        myScriptViewer.reset();
        myImageColorModule.reset();
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
        myToolbar.onBuildPress(e -> buildTree());
        myToolbar.onStepInstrPress(e -> runSingleInstruction());
        myToolbar.onStepLinePress(e -> runSingleLine());
        myToolbar.getButton(ButtonTypes.STEP_INSTRUCTION).setDisable(true);
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
        myPenPopup.getPenThickness();
    }

    private void buildTree() {
    	myModel.makeSyntaxTreeEvaluator(concatenateInstructions());
    	myToolbar.getButton(ButtonTypes.STEP_INSTRUCTION).setDisable(false);
    	myIndex = 0;
    	myTextEditor.clearHighlights();
    }
    
    private void runSingleInstruction(){
    	if(myModel.canStep()) myModel.stepInstruction();
    	else myToolbar.getButton(ButtonTypes.STEP_INSTRUCTION).setDisable(true);
    }
    
    private void runSingleLine(){
    	if(myIndex >= myTextEditor.getInstructionList().size()) return;
    	myModel.inputAll(myTextEditor.getInstructionList().get(myIndex));
    	myTextEditor.highlightLine(Color.GREEN, myIndex + 1);
    	myIndex++;
    }
    
    private void popupPenSelector () {
        Stage stage = new Stage();
        myPenPopup.initPopup(myImageColorModule);
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
