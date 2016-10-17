package applicationController;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class ApplicationController {

   private BorderPane myApplicationView;
   private Scene myScene;
   private Group myRoot;
   private String TITLE = "SLOGO";
   
   public ApplicationController() {
       myApplicationView = new BorderPane();
   }
   
   public Scene init (int width, int height) {
       myRoot = new Group();
       myRoot.getChildren().addAll(myApplicationView);
       myScene = new Scene(myRoot, width, height, Color.WHITE);
       return myScene;
   }
   
   public String getTitle() {
       return TITLE;
   }
}
