package front_end.view_modules.penProperties;

import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Kayla Schulz
 *
 */
public class PenPopup {

    private Pane layout;
    private HBox myRow;
    private static final int POPUP_SIZE = 600;
    
    public Scene initPopup(){
        myRow = new HBox();
        //TODO: Should this be a border or stack pane?
        layout = new Pane();
        layout.setStyle("-fx-background-color: paleturquoise;");
        layout.setPrefSize(POPUP_SIZE, POPUP_SIZE);
        makeColorPicker();
        Scene myScene = new Scene(layout);
        return myScene;
    }
    
    private void makeColorPicker() {
        Label penColorLabel = new Label("Choose your pen color: ");
        ColorPicker myColorPicker = new ColorPicker();
        myRow.getChildren().addAll(penColorLabel, myColorPicker);
        layout.getChildren().add(myRow);
    }
    
}
