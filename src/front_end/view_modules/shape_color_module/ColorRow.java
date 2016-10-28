package front_end.view_modules.shape_color_module;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * @author George Bernard
 *
 *	Entirely Package Specific, Encodes a row for the ColorModule
 *	Each row should have a set index, and a colorpicker
 */
class ColorRow {

	private HBox myRow;
	private int myIndex;
	private ColorPicker myColorPicker;
	
	private static final String COLOR_TEXT = "Color ID: ";
	private static final int SPACING = 5;
	private static final int CHARACTER_SIZE = 50;
	
	private ColorRow(){
		myRow = new HBox(SPACING);
		myColorPicker = new ColorPicker();
	}
	
	ColorRow(int aColorID){
		this();
		myIndex = aColorID;
		buildRow();
	}
	
	ColorRow(int aColorID, Color aDefaultColor){
		this(aColorID);
		myColorPicker.setValue(aDefaultColor);
	}
		
	Node getAsNode(){
		return myRow;
	}
	
	Color getColor(){
		return myColorPicker.getValue();
	}

	private void buildRow(){
		Label idLabel = new Label( COLOR_TEXT + Integer.toString(myIndex) );
		myRow.getChildren().addAll(idLabel, myColorPicker);
	}
}
