package front_end.view_modules.penProperties;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Kayla Schulz
 *
 */
public class PenPopup {

    private BorderPane layout;
    private HBox myRow;
    private static final int POPUP_SIZE = 600;
    private static final int SPACING = 10;
    
    public Scene initPopup(){
        myRow = new HBox(SPACING);
        layout = new BorderPane();
        layout.setStyle("-fx-background-color: paleturquoise;");
        layout.setPrefSize(POPUP_SIZE, POPUP_SIZE);
        makeColorPicker();
        createEndingButtons();
        Scene myScene = new Scene(layout);
        return myScene;
    }
    
    private void makeColorPicker() {
        Label penColorLabel = new Label("Choose your pen color: ");
        ColorPicker myColorPicker = new ColorPicker();
        myRow.setPadding(new Insets(5, 20, 10, 20));
        myRow.getChildren().addAll(penColorLabel, myColorPicker);
        layout.setTop(myRow);
    }
    
    private void createEndingButtons() {
        Button applyButton = makeButton("Apply Changes");
        Button clearButton = makeButton("Clear");
        HBox testBox = new HBox(SPACING);
        testBox.setPadding(new Insets(0, 20, 10, 20));
        testBox.setAlignment(Pos.CENTER_RIGHT);
        testBox.getChildren().addAll(clearButton, applyButton);
        layout.setBottom(testBox);
    }
    
    private Button makeButton(String buttonText) {
        Button addButton = new Button(buttonText);
        return addButton;
    }
    
}
