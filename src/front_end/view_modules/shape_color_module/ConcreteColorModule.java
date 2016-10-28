package front_end.view_modules.shape_color_module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import front_end.view_modules.shape_color_module.interfaces.IColorModule;
import integration.languages.Languages;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

class ConcreteColorModule implements IColorModule {

	private List<ColorRow> myColorRowList;
	private VBox myColumn;
	private ResourceBundle myDefaultBundle;
	
	private static final String INIT_FILE = "resources.frontend.color_module.";
	private static final String FILENAME = "DefaultColors";
	
	ConcreteColorModule(){
		myColorRowList = new ArrayList<>();
		myColumn = new VBox();
		myDefaultBundle = ResourceBundle.getBundle(INIT_FILE + FILENAME);
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
		
		for (ColorRow colorRow : myColorRowList) {
			
		}
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public Node getInstanceAsNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void switchLanguage(Languages aLanguage) {
		// TODO Auto-generated method stub

	}

	@Override
	public Color getColor(int aColorId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
