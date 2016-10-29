package front_end.view_modules.shape_color_module;

import java.io.File;
import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
	private ImageView myImageView;
	private Button myImageSelect;
	private Button myDeleteRow;
	private File myFile;
	
	private static final String IMAGE_TEXT = "Image ID: ";
	private static final String INIT_BUTTON_TEXT = "Select an Image";
	private static final String COMP_BUTTON_TEXT = "Switch Image";
	private static final String IMAGE_RESOURCE_LOC = "src/resources/images/";
	private static final String DELETE_TEXT = "Delete Row";
	
	private static final int SPACING = 5;
	private static final int CHARACTER_SIZE = 50;
	
	private ImageRow() {
		myRow = new HBox(SPACING);
		myImageSelect = new Button();
		myDeleteRow = new Button();
	}
	
	ImageRow(int aImageID) {
		this();
		myIndex = aImageID;
		buildIncompleteRow();
	}
	
	ImageRow(int aImageID, String aFilename) {
		this(aImageID, new File( IMAGE_RESOURCE_LOC + aFilename));
	}
	
	ImageRow(int aImageID, File aFile) {
		this(aImageID);
		loadImage( aFile );
		buildCompleteRow();
	}
	
	Node getAsNode() {
		return myRow;
	}
	
	File getFile() {
		return myFile;
	}
	
	
	private void openFileChooser(FileChooser chooseFile){
		myFile = chooseFile.showOpenDialog(new Stage());
		if(myFile != null){
			loadImage(myFile);
		}
	}
	
	private void selectFile() {
		FileChooser choose = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.gif");
		choose.getExtensionFilters().add(extFilter);
		
		openFileChooser( choose );
		buildCompleteRow();
	}
	
	private void loadImage(File aFile) {	
		myFile = aFile;
		myImageView = new ImageView( new Image( myFile.toURI().toString() ) );
		myImageView.setFitHeight(CHARACTER_SIZE);
		myImageView.setFitWidth(CHARACTER_SIZE);
	}	

	private void buildIncompleteRow(){
		Label idLabel = new Label( IMAGE_TEXT + Integer.toString(myIndex));
		
		VBox imageMenu = new VBox(0);
		myImageSelect.setText(INIT_BUTTON_TEXT);
		myImageSelect.setOnMouseClicked( event -> selectFile() );
		myDeleteRow.setText(DELETE_TEXT);
		myDeleteRow.setOnMouseClicked( event -> myRow.getChildren().clear());
		imageMenu.getChildren().addAll(myImageSelect, myDeleteRow);
		
		myRow.getChildren().addAll(idLabel, imageMenu);
	}
	
	private void buildCompleteRow(){
		myRow.getChildren().clear();
		buildIncompleteRow();
		myImageSelect.setText(COMP_BUTTON_TEXT);
		myRow.getChildren().add(myImageView);
	}
}
