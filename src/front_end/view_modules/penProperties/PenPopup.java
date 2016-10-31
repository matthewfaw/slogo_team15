package front_end.view_modules.penProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import integration.drawing.LineStyleSpec;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    private static final int MAX_PEN_THICKNESS = 30;
    private Button applyButton;
    private Button clearButton;
    private Scene myScene;
    private ColorPicker myColorPicker;
    private HBox closingButtonBox;
    private ToggleButton myPenDownButton;
    private ToggleButton myPenUpButton;
    private VBox myOrder;

    @Override
    public void initPopup () {
        myRow = new HBox(SPACING);
        myOrder = new VBox(SPACING);
        layout = new BorderPane();
        layout.setStyle("-fx-background-color: paleturquoise;");
        layout.setPrefSize(POPUP_SIZE, POPUP_SIZE);
        makeColorPicker();
        createEndingButtons();
        penUpOrDown();
        setBorderPane();
        myScene = new Scene(layout);
    }

    @Override
    public Scene getScene () {
        return myScene;
    }

    private List<Integer> penThicknessOptions () {
        List<Integer> myList = new ArrayList<Integer>();
        for (int i = 1; i < MAX_PEN_THICKNESS; i++) {
            myList.add(i);
        }
        return myList;
    }

    private void penUpOrDown () {
        myPenDownButton = new ToggleButton("Pen Down");
        myPenUpButton = new ToggleButton("Pen Up");
        ToggleGroup group1 = new ToggleGroup();
        group1.selectedToggleProperty()
                // Set Change Text if toggled
                .addListener( (ObservableValue<? extends Toggle> ov,
                               Toggle old_toggle,
                               Toggle new_toggle) -> {
                    myPenDownButton.setText("Pen Down");
                    if (new_toggle == null)
                        return;
                    if (new_toggle.isSelected())
                        myPenUpButton.setSelected(false);
                });
        ToggleGroup group2 = new ToggleGroup();
        group2.selectedToggleProperty()
        .addListener( (ObservableValue<? extends Toggle> ov,
                Toggle old_toggle_penup, Toggle new_toggle_penup) -> {
                    myPenUpButton.setText("Pen Up");
                    if(new_toggle_penup == null)
                        return;
                    if(new_toggle_penup.isSelected())
                        myPenDownButton.setSelected(false);
                });
        myPenDownButton.setToggleGroup(group1);
        myPenUpButton.setToggleGroup(group2);
    }

    private void setBorderPane () {
        myOrder.getChildren().add(myRow);
        myOrder.getChildren().add(createComboBox("Pen thickness: ", penThicknessOptions()));
        myOrder.getChildren().add(createComboBox("Line Style: ", myLineStyleOptions()));
        HBox penUpBox = new HBox(SPACING);
        penUpBox.getChildren().addAll(myPenUpButton, myPenDownButton);
        penUpBox.setPadding(new Insets(5, 20, 10, 20));
        myOrder.getChildren().add(penUpBox);
        layout.setCenter(myOrder);
        layout.setBottom(closingButtonBox);
    }
    
    private List<LineStyleSpec> myLineStyleOptions () { 
        return Arrays.asList(LineStyleSpec.values());
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
        closingButtonBox = new HBox(SPACING);
        closingButtonBox.setPadding(new Insets(0, 20, 10, 20));
        closingButtonBox.setAlignment(Pos.CENTER_RIGHT);
        closingButtonBox.getChildren().addAll(clearButton, applyButton);
    }

    private HBox createComboBox (String labelName, List myOptions) {
        HBox comboBoxRow = new HBox(SPACING);
        comboBoxRow.setPadding(new Insets(5, 20, 10, 20));
        Label comboBoxLabel = new Label(labelName);
        ComboBox addComboBox = new ComboBox();
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
        // TODO: Figure out how to keep createComboBox method and be able to access here
        myPenDownButton.setSelected(false);
        myPenUpButton.setSelected(true);
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
