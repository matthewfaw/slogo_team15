package front_end.view_modules.image_color_module.color;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import front_end.view_modules.image_color_module.interfaces.IColorModule;
import integration.languages.Languages;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

class ConcreteColorModule implements IColorModule {

	private List<ColorRow> myColorRowList;
	private VBox myColorModuleBox;
	private VBox myColumn;
	private ResourceBundle myDefaultBundle;
	
	private static final String INIT_FILE = "resources.frontend.color_module.";
	private static final String FILENAME = "DefaultColors";
	
	ConcreteColorModule(){
		myColorRowList = new ArrayList<>();
		
		myColorModuleBox = new VBox();
		initNewColorButton();
		
		myColumn = new VBox();
		myColorModuleBox.getChildren().add(myColumn);
		
		myDefaultBundle = ResourceBundle.getBundle(INIT_FILE + FILENAME);
		
		setDefault();
		setColumn();
	}
	
	private void setDefault() {
		myColorRowList.clear();
		
		int i = 0;
		
		for( String key : myDefaultBundle.keySet()){
			myColorRowList.add(i, new ColorRow( i++, 
					Color.web(myDefaultBundle.getString(key))
					));
			}
	}
	
	private void setColumn() {
		myColumn.getChildren().clear();
		myColorRowList.forEach( cr -> myColumn.getChildren().add(cr.getAsNode()) );
	}
	
	private void initNewColorButton(){
		Button newColorButton = new Button("New Color!"); 
		newColorButton.setOnMouseClicked(event -> addColorRow());
		myColorModuleBox.getChildren().add(newColorButton);
	}
	
	private void addColorRow(){
		myColorRowList.add(new ColorRow(myColorRowList.size()));
		setColumn();
	}
	
	@Override
	public void reset() {
		setDefault();
		setColumn();
	}

	@Override
	public Node getInstanceAsNode() {
		return myColorModuleBox;
	}

	@Override
	public void switchLanguage(Languages aLanguage) {
		// TODO Auto-generated method stub
	}

	@Override
	public Color getColor(int aColorId) {
		return myColorRowList.get(aColorId).getColor();
	}

	
}
