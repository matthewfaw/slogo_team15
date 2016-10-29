package front_end.view_modules.shape_color_module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import front_end.view_modules.shape_color_module.interfaces.IShapeModule;
import integration.languages.Languages;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

class ConcreteShapeModule implements IShapeModule {

	private List<ImageRow> myImageRowList;
	private VBox myImageModuleBox;
	private VBox myColumn;
	private ResourceBundle myDefaultImageBundle;
	private ResourceBundle myTextBundle;
	
	private static final String INIT_FILE = "resources.frontend.shape_module.";
	private static final String IMAGE_FILENAME = "DefaultImages";
	private static final String TEXT_FILENAME = "ShapeModuleText";
	
	ConcreteShapeModule(){
		myDefaultImageBundle = ResourceBundle.getBundle(INIT_FILE + IMAGE_FILENAME);
		myTextBundle = ResourceBundle.getBundle(INIT_FILE  + TEXT_FILENAME);
		
		myImageRowList = new ArrayList<>();
		
		myImageModuleBox = new VBox();
		initNewImageButton();
		
		myColumn = new VBox();
		myImageModuleBox.getChildren().add(myColumn);
		
		setDefault();
		setColumn();	
	}
	
	private void setDefault() {
		myImageRowList.clear();
		
		int i = 0;
		
		for( String key : myDefaultImageBundle.keySet()){
			myImageRowList.add(i, new ImageRow( i++, myDefaultImageBundle.getString(key)	));
			}
	}

	private void setColumn() {
		myColumn.getChildren().clear();
		myImageRowList.forEach( ir -> myColumn.getChildren().add(ir.getAsNode()) );
	}
	
	private void initNewImageButton(){
		Button newImageButton = new Button( myTextBundle.getString( "NewRow" ) ); 
		newImageButton.setOnMouseClicked(event -> addImageRow());

		myImageModuleBox.getChildren().add(newImageButton);
	}
	
	private void addImageRow(){
		myImageRowList.add(new ImageRow(myImageRowList.size()));
		setColumn();
	}
	
	@Override
	public void reset() {
		setDefault();
		setColumn();
	}
	
	@Override
	public Node getInstanceAsNode() {
		// TODO Auto-generated method stub
		return myImageModuleBox;
	}

	@Override
	public void switchLanguage(Languages aLanguage) {
		// TODO Auto-generated method stub

	}

	@Override
	public File getFile(int aImageId) {
		return myImageRowList.get(aImageId).getFile();
	}

}
