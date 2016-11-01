package front_end.appScene;

import front_end.view_modules.errorViewer.ErrorViewerFactory;
import front_end.view_modules.errorViewer.IErrorViewer;
import front_end.view_modules.function_viewer.FunctionViewerFactory;
import front_end.view_modules.function_viewer.IFunctionViewer;
import front_end.view_modules.helpPage.HelpPage;
import front_end.view_modules.history.HistoryModuleFactory;
import front_end.view_modules.history.IHistoryModule;
import front_end.view_modules.image_color_module.ImageColorModuleFactory;
import front_end.view_modules.image_color_module.interfaces.IImageColorModule;
import front_end.view_modules.textEditor.ITextEditor;
import front_end.view_modules.textEditor.TextEditorFactory;
import front_end.view_modules.toolbar.IToolbar;
import front_end.view_modules.toolbar.ToolbarFactory;
import front_end.view_modules.turtleBox.ITurtleBox;
import front_end.view_modules.turtleBox.TurtleBoxFactory;
import front_end.view_modules.turtlestate.ConcreteAllRobotsStateBox;
import front_end.view_modules.turtlestate.IAllRobotsStateBox;
import front_end.view_modules.variableViewer.IVariableViewer;
import front_end.view_modules.variableViewer.VariableViewerFactory;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
	private IFunctionViewer myFunctionViewer;
	private IImageColorModule myShapeColorModule;
	private IAllRobotsStateBox myStatesBox;
	private IHistoryModule myHistoryModule;
	private HelpPage myHelpPage;

	public ApplicationScene (int aWidth, int aHeight) {
		myApplicationView = new GridPane();

		myHistoryModule = 		 HistoryModuleFactory.build( aWidth / 3 , 5 * aHeight / 12 );
		myToolbar = 				   ToolbarFactory.build( aWidth		, aHeight / 20 );
		myTextEditor = 	 			TextEditorFactory.build( aWidth / 2 , aHeight / 2);
		myErrorViewer = 		   ErrorViewerFactory.build( aWidth / 2 , aHeight / 12 , myTextEditor);
		myVariableViewer = 		VariableViewerFactory.build( aWidth / 4 , aHeight / 2 - aHeight/20 );
		myFunctionViewer = 		FunctionViewerFactory.build( aWidth / 4 , aHeight / 2 - aHeight/20 );
		myShapeColorModule =  ImageColorModuleFactory.build( aWidth / 4 , aHeight / 4  );
		myTurtleBox = 				 TurtleBoxFactory.build( aWidth / 2 ,  aHeight / 2, myShapeColorModule);
		myStatesBox = new ConcreteAllRobotsStateBox(myShapeColorModule, myShapeColorModule);
		myHelpPage = new HelpPage();

		myRoot = new Group();
		myRoot.getChildren().addAll(myApplicationView);
		myScene = new Scene(myRoot, aWidth, aHeight + aHeight/20, Color.WHITE);
		myApplicationView.add(myToolbar.getInstanceAsNode(), 			0, 0, GridPane.REMAINING, 1);
		myApplicationView.add(myTurtleBox.getInstanceAsNode(), 			0, 1, 1, 1);
		myApplicationView.add(myTextEditor.getInstanceAsNode(), 		0, 2, 1, 1);
		myApplicationView.add(join(myStatesBox.getInstanceAsNode(), 
							  myShapeColorModule.getInstanceAsNode()), 	1, 1, 1, 1);
		myApplicationView.add(joinSwitch(myVariableViewer.getInstanceAsNode(), myFunctionViewer.getInstanceAsNode(), aHeight / 2, aWidth/4), 2, 1, 1, 1);
		myApplicationView.add(join(myErrorViewer.getInstanceAsNode(),
								 myHistoryModule.getInstanceAsNode()),	1, 2, 2, 1 );

	}
	
	private Node joinSwitch(Node aVarViewer, Node aFunctionViewer, int aHeight, int aWidth){
	StackPane pane = new StackPane();
	pane.setMaxSize(aWidth, aHeight);
	VBox box = new VBox(0);
	pane.getChildren().add(box);
	
	HBox toolbar = new HBox(0);
	VBox column = new VBox(0);
	box.getChildren().addAll(toolbar, column);
	
	String var = "Variables";
	String fun = "Functions";
	
	javafx.collections.ObservableList<String> tabs = FXCollections.observableArrayList("Variables", "Functions");
	
	ComboBox<String> switchTabs = new ComboBox<>(tabs);
	switchTabs.getSelectionModel().selectedItemProperty().addListener( (Ov, old, neu) -> {
		column.getChildren().clear();
		if(neu.equals(var)) column.getChildren().add(aVarViewer);
		if(neu.equals(fun)) column.getChildren().add(aFunctionViewer);
		});
	toolbar.getChildren().add(switchTabs);
	
	switchTabs.getSelectionModel().select(fun);

	return pane;
	}
	
	private Node join(Node... nodes){
		StackPane pane = new StackPane();
		VBox join = new VBox(0);
		pane.getChildren().add(join);
		
		for (Node node : nodes) {
			join.getChildren().add(node);
		}
		
		return pane;
	} 
	
	
	public Scene getScene () {
		return myScene;
	}

	public Group getMyRoot () {
		return myRoot;
	}

	public HelpPage getMyHelpPage () {
		return myHelpPage;
	}

	public ITurtleBox getMyTurtleBox () {
		return myTurtleBox;
	}

	public IFunctionViewer getMyFunctionViewer () {
		return myFunctionViewer;
	}

	public ITextEditor getMyTextEditor () {
		return myTextEditor;
	}

	public IErrorViewer getMyErrorViewer () {
		return myErrorViewer;
	}

	public IVariableViewer getMyVariableViewer () {
		return myVariableViewer;
	}

	public IToolbar getMyToolbar () {
		return myToolbar;
	}

	public IImageColorModule getMyShapeColorModule () {
		return myShapeColorModule;
	}

	public IAllRobotsStateBox getMyStatesBox() {
		return myStatesBox;
	}
	
	public IHistoryModule getMyHistoryModule(){
		return myHistoryModule;
	}
}
