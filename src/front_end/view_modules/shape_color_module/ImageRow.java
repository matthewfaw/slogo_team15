package front_end.view_modules.shape_color_module;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * @author George Bernard
 *
 *	Entirely Package Specific, Encodes a row for the ImageModule
 *	Each row should have a set index, a button to switch image, and thumbnail
 *
 *	If no image has been selected, it should have a select image button
 */
class ImageRow {
	private HBox myRow;
	private int myIndex;
	private String myImageFilename;
	private ImageView myImageView;
	private Button myImageSelect;
	
	private boolean myImageSelected;
	
	private static final String IMAGE_TEXT = "Image ID: ";
	private static final String INIT_BUTTON_TEXT = "Select an Image";
	private static final String COMP_BUTTON_TEXT = "Switch Image";
	
	private static final int SPACING = 5;
	private static final int CHARACTER_SIZE = 50;
	
	private ImageRow() {
		myRow = new HBox(SPACING);
		myImageSelect = new Button();
		myImageSelected = false;
	}
	
	ImageRow(int aImageID) {
		this();
		myIndex = aImageID;
		buildIncompleteRow();
	}
	
	ImageRow(int aImageID, String aFilename) {
		this(aImageID);
		loadImage(aFilename);
		buildCompleteRow();
	}
	
	Node getAsNode() {
		return myRow;
	}
	
	String getFilename() {
		return myImageFilename;
	}
	
	private void loadImage(String aFilename) {
		myImageFilename = aFilename;
		
		myImageView = new ImageView( new Image(myImageFilename) );
		myImageView.setFitHeight(CHARACTER_SIZE);
		myImageView.setFitWidth(CHARACTER_SIZE);
		
		myImageSelected = true;
	}	

	private void buildIncompleteRow(){
		Label idLabel = new Label( IMAGE_TEXT + Integer.toString(myIndex));
		myImageSelect.setText(INIT_BUTTON_TEXT);
		myRow.getChildren().addAll(idLabel, myImageSelect);
	}
	
	private void buildCompleteRow(){
		myRow.getChildren().clear();
		buildIncompleteRow();
		myImageSelect.setText(COMP_BUTTON_TEXT);
		myRow.getChildren().add(myImageView);
	}
}
