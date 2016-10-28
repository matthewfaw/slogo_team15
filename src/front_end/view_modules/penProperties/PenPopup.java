package front_end.view_modules.penProperties;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class PenPopup implements IPenPopup {

    private BorderPane layout;
    private HBox myRow;
    private static final int POPUP_SIZE = 600;
    private static final int SPACING = 10;
    private Button applyButton;
    private Button clearButton;
    private Scene myScene;
    private ColorPicker myColorPicker;
    private HBox testBox;

    @Override
    public void initPopup () {
        myRow = new HBox(SPACING);
        layout = new BorderPane();
        layout.setStyle("-fx-background-color: paleturquoise;");
        layout.setPrefSize(POPUP_SIZE, POPUP_SIZE);
        makeColorPicker();
        createEndingButtons();
        setBorderPane();
        myScene = new Scene(layout);
    }

    @Override
    public Scene getScene () {
        return myScene;
    }

    private void setBorderPane () {
        layout.setTop(myRow);
        layout.setBottom(testBox);
        layout.setCenter(createComboBox("Pen thickness: ", new ArrayList<String>()));
    }

    private void makeColorPicker () {
        Label penColorLabel = new Label("Choose your pen color: ");
        myColorPicker = new ColorPicker(Color.WHITE);
        myRow.setPadding(new Insets(5, 20, 10, 20));
        myRow.getChildren().addAll(penColorLabel, myColorPicker);
    }

    private void createEndingButtons () {
        applyButton = makeButton("Apply Changes");
        clearButton = makeButton("Clear");
        testBox = new HBox(SPACING);
        testBox.setPadding(new Insets(0, 20, 10, 20));
        testBox.setAlignment(Pos.CENTER_RIGHT);
        testBox.getChildren().addAll(clearButton, applyButton);
        
    }

    private HBox createComboBox (String labelName, List<String> myOptions) {
        HBox comboBoxRow = new HBox(SPACING);
        Label comboBoxLabel = new Label(labelName);
        ComboBox<String> addComboBox = new ComboBox<String>();
        addComboBox.getItems().addAll(myOptions);
        comboBoxRow.getChildren().addAll(comboBoxLabel, addComboBox);
        return comboBoxRow;
    }

    private Button makeButton (String buttonText) {
        Button addButton = new Button(buttonText);
        return addButton;
    }

    @Override
    public void clear () {
        myRow.getChildren().clear();
        makeColorPicker();
    }

    @Override
    public void onApplyPress (EventHandler<MouseEvent> aEvent) {
        applyButton.setOnMouseClicked(aEvent);
    }

    @Override
    public void onClearPress (EventHandler<MouseEvent> aEvent) {
        clearButton.setOnMouseClicked(aEvent);
    }
}
