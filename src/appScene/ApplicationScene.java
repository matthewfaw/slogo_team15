package appScene;

import appScene.errorViewer.ErrorViewerFactory;
import appScene.errorViewer.IErrorViewer;
import appScene.scriptViewer.IScriptViewer;
import appScene.scriptViewer.ScriptViewerFactory;
import appScene.textEditor.ITextEditor;
import appScene.textEditor.TextEditorFactory;
import appScene.toolbar.IToolbar;
import appScene.toolbar.ToolbarFactory;
import appScene.turtleBox.ITurtleBox;
import appScene.turtleBox.TurtleBoxFactory;
import appScene.variableViewer.IVariableViewer;
import appScene.variableViewer.VariableViewerFactory;
import controller.ModelController;
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
    
    public ApplicationScene() {
        myApplicationView = new GridPane();
        myModel = new ModelController();
    }
    
    public Scene initScene(int aWidth, int aHeight) {
        myToolbar = ToolbarFactory.buildToolbar(aWidth, aHeight / 20);
        myTurtleBox = TurtleBoxFactory.buildTurtleBox(2*aWidth/3, 2*aHeight/3);
        myTextEditor = TextEditorFactory.buildTextEditor( 2*aWidth/3, aHeight / 3);
        myErrorViewer = ErrorViewerFactory.buildErrorViewer( aWidth/3, aHeight / 3, myTextEditor);
        myVariableViewer = VariableViewerFactory.buildVariableViewer( aWidth / 6, aHeight / 3);
        myScriptViewer = ScriptViewerFactory.buildViewerFactory( aWidth / 6, aHeight / 3); // double check these values

        myRoot = new Group();
        myRoot.getChildren().addAll(myApplicationView);
        myScene = new Scene(myRoot, aWidth, aHeight + aHeight/20 + 10, Color.WHITE);
        myApplicationView.add(myToolbar.getInstanceAsNode(),            0, 0, GridPane.REMAINING, 1);
        myApplicationView.add(myTurtleBox.getInstanceAsNode(),          0, 1, 1, 1);
        myApplicationView.add(myTextEditor.getInstanceAsNode(),         0, 2, 1, 1);
        myApplicationView.add(myVariableViewer.getInstanceAsNode(), 	1, 1, 1, 1);
        myApplicationView.add(myScriptViewer.getInstanceAsNode(),   	2, 1, 1, 1);
        myApplicationView.add(myErrorViewer.getInstanceAsNode(),        1, 2, 2, 1);
        
        myModel.giveRobotObservers(myTurtleBox);
        
        configureToolbar();
        
        return myScene;
    }
    
    private void configureToolbar(){
    	myToolbar.onResetPress(e -> {
    		
    		myTextEditor.reset();
    		myErrorViewer.reset();
    		myTurtleBox.reset();
    		myVariableViewer.reset();
    		myScriptViewer.reset();
    	
    	});
    	
    	myToolbar.onRunPress( e -> {
    		StringBuilder sb = new StringBuilder();
    		
    		String newLine = "\n";
    		
    		for (int i = 0; i < myTextEditor.getInstructionList().size(); i++) {
				sb.append( myTextEditor.getInstructionList().get(i) );
				sb.append( newLine );
			}
    		
    		try {
        		myModel.userInputToModel(sb.toString());
			} catch (Exception e2) {
				//TODO: Implement error viewing
			} 
    		
  
    	});
    	
   }
    
}
