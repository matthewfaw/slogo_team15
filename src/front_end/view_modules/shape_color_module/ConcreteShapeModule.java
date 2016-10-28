package front_end.view_modules.shape_color_module;

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
	private ResourceBundle myDefaultBundle;
	
	private static final String INIT_FILE = "resources.frontend.shape_module.";
	private static final String FILENAME = "DefaultImages";
	
	ConcreteShapeModule(){
		myImageRowList = new ArrayList<>();
		
		myImageModuleBox = new VBox();
		initNewImageButton();
		
		myColumn = new VBox();
		myImageModuleBox.getChildren().add(myColumn);
		
		myDefaultBundle = ResourceBundle.getBundle(INIT_FILE + FILENAME);
		
		setDefault();
		setColumn();	
	}
	
	private void setDefault() {
		myImageRowList.clear();
		
		int i = 0;
		
		for( String key : myDefaultBundle.keySet()){
			myImageRowList.add(i, new ImageRow( i++, myDefaultBundle.getString(key)	));
			}
	}

	private void setColumn() {
		myColumn.getChildren().clear();
		myImageRowList.forEach( ir -> myColumn.getChildren().add(ir.getAsNode()) );
	}
	
	private void initNewImageButton(){
		Button newImageButton = new Button("New Image!"); 
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
	public String getFilename(int aImageId) {
		return myImageRowList.get(aImageId).getFilename();
	}

}
