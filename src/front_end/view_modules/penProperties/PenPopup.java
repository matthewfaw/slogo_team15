package front_end.view_modules.penProperties;

import java.util.ArrayList;
import java.util.List;
import front_end.view_modules.image_color_module.interfaces.IColorModule;
import integration.drawing.LineStyleSpec;
import integration.drawing.PenInformation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private RadioButton myPenDownButton;
    private RadioButton myPenUpButton;
    private VBox myOrder;
    private String myLineStyle;
    private int myPenThickness;
    private IColorModule myColorModule;
    
    @Override
    public void initPopup (IColorModule aColorModule) {
        myRow = new HBox(SPACING);
        myOrder = new VBox(SPACING);
        layout = new BorderPane();
        layout.setStyle("-fx-background-color: paleturquoise;");
        layout.setPrefSize(POPUP_SIZE, POPUP_SIZE);
        myColorModule = aColorModule;
        makeColorPickerRow();
        createEndingButtons();
        penUpOrDown();
        setBorderPane();
        myScene = new Scene(layout);
    }

    @Override
    public Scene getScene () {
        return myScene;
    }

    private List<Object> penThicknessOptions () {
        List<Object> myList = new ArrayList<Object>();
        for (int i = 1; i < MAX_PEN_THICKNESS; i++) {
            myList.add(i);
        }
        return myList;
    }
    
    private List<Object> makeColorPaletteOptions() {
        List<Object> myList = new ArrayList<Object>();
        for(int i = 0; i < myColorModule.getColorAmount(); i++) {
            myList.add(i);
        }
        return myList;
    }

    private void penUpOrDown () {
        myPenDownButton = new RadioButton("Pen Down");
        myPenUpButton = new RadioButton("Pen Up");
        myPenUpButton.setSelected(true);
        myPenDownButton.setUserData("Pen Down");
        myPenUpButton.setUserData("Pen Up");
        
        ToggleGroup group1 = new ToggleGroup();
        group1.selectedToggleProperty()
                // Set Change Text if toggled
                .addListener( (ObservableValue<? extends Toggle> ov,
                               Toggle old_toggle,
                               Toggle new_toggle) -> {
                    if (new_toggle == null)
                        return;
                    if (new_toggle.isSelected()) {
                    }
                });
        myPenDownButton.setToggleGroup(group1);
        myPenUpButton.setToggleGroup(group1);
    }

    private HBox penThickness;

    private void setBorderPane () {
        myOrder.getChildren().add(myRow);
        myOrder.getChildren().add(createComboBox("Choose Palette Color: ", makeColorPaletteOptions()));
        penThickness = createComboBox("Pen thickness: ", penThicknessOptions());
        myOrder.getChildren().add(penThickness);
        myOrder.getChildren().add(createComboBox("Line Style: ", myLineStyleOptions()));
        
        HBox penUpBox = new HBox(SPACING);
        penUpBox.getChildren().addAll(myPenUpButton, myPenDownButton);
        penUpBox.setPadding(new Insets(5, 20, 10, 20));
        myOrder.getChildren().add(penUpBox);
        layout.setCenter(myOrder);
        layout.setBottom(closingButtonBox);
    }

    private List<Object> myLineStyleOptions () {
        return LineStyleSpec.getMyLineStyles();
    }

    private void makeColorPickerRow () {
        Label penColorLabel = new Label("Choose new pen color: ");
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

    private HBox createComboBox (String labelName, List<Object> myOptions) {
        HBox comboBoxRow = new HBox(SPACING);
        comboBoxRow.setPadding(new Insets(5, 20, 10, 20));
        Label comboBoxLabel = new Label(labelName);
        ComboBox<Object> addComboBox = new ComboBox<Object>();
        addComboBox.getItems().addAll(myOptions);
        addComboBox.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Object>() {
                    public void changed (ObservableValue<? extends Object> ov,
                                         final Object oldvalue,
                                         final Object newvalue) {
                        if (labelName.contains("Pen"))
                            setPenThickness(newvalue);
                        if (labelName.contains("Line"))
                            setLineStyle(newvalue);
                    }
                });
        comboBoxRow.getChildren().addAll(comboBoxLabel, addComboBox);
        return comboBoxRow;
    }

    private void setLineStyle (Object newvalue) {
        myLineStyle = newvalue.toString();
    }

    private void setPenThickness (Object newvalue) {
        myPenThickness = Integer.parseInt(newvalue.toString());
    }

    public Color getColorValue () {
        //TODO: Change this later
        myColorModule.newColorRow(myColorPicker.getValue());
        return myColorPicker.getValue();
    }

    public int getPenThickness () {
        return myPenThickness;
    }

    public String getLineStyle () {
        return myLineStyle;
    }

    private Button makeButton (String buttonText) {
        Button addButton = new Button(buttonText);
        return addButton;
    }

    @Override
    public void clear () {
        myRow.getChildren().clear();
        // TODO: Figure out how to keep createComboBox method and be able to access here
        // TODO: get pen to take its previous state
        myPenUpButton.setSelected(true);
        makeColorPickerRow();
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
