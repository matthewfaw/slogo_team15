package front_end.appScene;

import back_end.controller.ModelController;
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
    		
    		myVariableViewer.showVariables(myModel.getVariableMap());
  
    	});
    	
   }
    
}
