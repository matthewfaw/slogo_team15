package applicationController;

import appScene.errorViewer.ConcreteErrorViewer;
import appScene.textEditor.ConcreteTextEditor;
import appScene.toolbar.ConcreteToolbar;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class ApplicationController {

   private BorderPane myApplicationView;
   private Scene myScene;
   private Group myRoot;
   private String TITLE = "SLOGO";
   private ConcreteToolbar myToolbar;
   private ConcreteTextEditor myTextEditor;
   private ConcreteErrorViewer myErrorViewer;
   
   public ApplicationController() {
       myApplicationView = new BorderPane();
   }
   
   public Scene init (int width, int height) {
       myToolbar = new ConcreteToolbar();
       myTextEditor = new ConcreteTextEditor();
       myErrorViewer = new ConcreteErrorViewer(width, height);
       
       
       myRoot = new Group();
       myRoot.getChildren().addAll(myApplicationView);
       myScene = new Scene(myRoot, width, height, Color.WHITE);
       myApplicationView.setTop(myToolbar.getToolbarAsNode(width, height));
       myApplicationView.setCenter(myTextEditor.initTextEditor(width, height));
       myApplicationView.setRight( myErrorViewer.getInstanceAsNode() );
       return myScene;
   }
   
   public String getTitle() {
       return TITLE;
   }
}
