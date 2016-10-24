package front_end.appScene;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import back_end.controller.ModelController;
import back_end.model.exception.EmptyInputException;
import back_end.model.exception.UnexpectedCharacterException;
import back_end.model.exception.UnexpectedCommandException;
import front_end.appScene.errorViewer.ErrorViewerFactory;
import front_end.appScene.errorViewer.IErrorViewer;
import front_end.appScene.scriptViewer.IScriptViewer;
import front_end.appScene.scriptViewer.ScriptViewerFactory;
import front_end.appScene.textEditor.ITextEditor;
import front_end.appScene.textEditor.TextEditorFactory;
import front_end.appScene.toolbar.IToolbar;
import front_end.appScene.toolbar.ToolbarFactory;
import front_end.appScene.turtleBox.ITurtleBox;
import front_end.appScene.turtleBox.TurtleBoxFactory;
import front_end.appScene.variableViewer.IVariableViewer;
import front_end.appScene.variableViewer.VariableViewerFactory;
import integration.languages.Languages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class ApplicationScene {

    private GridPane myApplicationView;
    private Scene myScene;
    private Group myRoot;
    private IToolbar myToolbar;
    private ITextEditor myTextEditor;
    private IErrorViewer myErrorViewer;
    private ITurtleBox myTurtleBox;
    private IVariableViewer myVariableViewer;
    private IScriptViewer myScriptViewer;

    private ModelController myModel;

    public ApplicationScene () {
        myApplicationView = new GridPane();
        myModel = new ModelController();
    }

    public Scene initScene (int aWidth, int aHeight) {
        myToolbar = ToolbarFactory.buildToolbar(aWidth, aHeight / 20);
        myTurtleBox = TurtleBoxFactory.buildTurtleBox(2 * aWidth / 3, 2 * aHeight / 3);
        myTextEditor = TextEditorFactory.buildTextEditor(2 * aWidth / 3, aHeight / 3);
        myErrorViewer = ErrorViewerFactory.buildErrorViewer(aWidth / 3, aHeight / 3, myTextEditor);
        myVariableViewer = VariableViewerFactory.buildVariableViewer(aWidth / 6, aHeight / 3);
        myScriptViewer = ScriptViewerFactory.buildViewerFactory(aWidth / 6, aHeight / 3); // double
                                                                                          // check
                                                                                          // these
                                                                                          // values

        myRoot = new Group();
        myRoot.getChildren().addAll(myApplicationView);
        myScene = new Scene(myRoot, aWidth, aHeight + aHeight / 20 + 10, Color.WHITE);
        myApplicationView.add(myToolbar.getInstanceAsNode(), 0, 0, GridPane.REMAINING, 1);
        myApplicationView.add(myTurtleBox.getInstanceAsNode(), 0, 1, 1, 1);
        myApplicationView.add(myTextEditor.getInstanceAsNode(), 0, 2, 1, 1);
        myApplicationView.add(myVariableViewer.getInstanceAsNode(), 1, 1, 1, 1);
        myApplicationView.add(myScriptViewer.getInstanceAsNode(), 2, 1, 1, 1);
        myApplicationView.add(myErrorViewer.getInstanceAsNode(), 1, 2, 2, 1);

        myModel.giveRobotObservers(myTurtleBox);

        configureToolbar();

        return myScene;
    }

    private void resetAll () {
        myTextEditor.reset();
        myErrorViewer.reset();
        myTurtleBox.reset();
        myVariableViewer.reset();
        myScriptViewer.reset();
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

        myToolbar.onLanguageSelect(makeLanguageMap());

    }

}
