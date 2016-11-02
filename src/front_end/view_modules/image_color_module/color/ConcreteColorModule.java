package front_end.view_modules.image_color_module.color;

import java.util.ArrayList;
import java.util.List;

import back_end.model.states.background.IViewableColorPalette;
import front_end.view_modules.image_color_module.interfaces.IColorModule;
import integration.observe.IObserver;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

class ConcreteColorModule implements IColorModule, IObserver {

	private List<ColorRow> myColorRowList;
	private IViewableColorPalette myViewPalette;
	private VBox myColorModuleBox;
	private VBox myColumn;
	
	ConcreteColorModule(){
		myColorRowList = new ArrayList<>();
		
		myColorModuleBox = new VBox();
		initNewColorButton();
		
		myColumn = new VBox();
		myColorModuleBox.getChildren().add(myColumn);

		setColumn();
	}
	
	private void setDefault() {
		myColorRowList.clear();
		
		for( Integer i : myViewPalette.getPaletteColors()){
			myColorRowList.add( new ColorRow( i, Color.web( myViewPalette.getHexadecimalColor(i) ) ) );
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
		myColorRowList.add(new ColorRow(getColorAmount()));
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
	public Color getColor(int aColorId) {
		return myColorRowList.get(aColorId).getColor();
	}

	@Override
	public int getColorAmount() {
		return myColorRowList.size();
	}

	@Override
	public void newColorRow(Color aColor) {
		myColorRowList.add(new ColorRow(getColorAmount(), aColor));
	}

	@Override
	public void giveColorPalette(IViewableColorPalette aViewPalette) {
		myViewPalette = aViewPalette;
		myViewPalette.registerObserver(this);
		setDefault();
		setColumn();
	}

	@Override
	public void update() {
		setDefault();
		setColumn();
	}

	
}
