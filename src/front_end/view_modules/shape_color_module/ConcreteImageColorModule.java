package front_end.view_modules.shape_color_module;

import java.io.File;

import front_end.view_modules.shape_color_module.color.ColorModuleFactory;
import front_end.view_modules.shape_color_module.image.ImageModuleFactory;
import front_end.view_modules.shape_color_module.interfaces.IColorModule;
import front_end.view_modules.shape_color_module.interfaces.IImageColorModule;
import front_end.view_modules.shape_color_module.interfaces.IImageModule;
import integration.languages.Languages;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

class ConcreteImageColorModule implements IImageColorModule {

	private ScrollPane myWindow;
	private VBox myModule;
	private VBox myModuleSwitchBox;
	
	private MenuButton mySwitchModulesMenu;
	
	private enum Palettes{
		COLOR("Color Palette"),
		SHAPE("Image Palette");
		
		private String myName;
		
		Palettes(String aName){
			myName = aName;
		}
		
		String getName(){
			return myName;
		}
	}
	
	private IColorModule myColorModule;
	private IImageModule myImageModule;
	
	private static final Palettes DEFAULT_PALETTE = Palettes.COLOR;
	
	ConcreteImageColorModule(){
		myColorModule = ColorModuleFactory.build();
		myImageModule = ImageModuleFactory.build();
		
		myWindow = new ScrollPane();
		myWindow.setHbarPolicy( ScrollBarPolicy.NEVER );
		myWindow.setVbarPolicy( ScrollBarPolicy.ALWAYS );
		
		myModuleSwitchBox = new VBox(0);
		myModuleSwitchBox.getChildren().add(myColorModule.getInstanceAsNode());
		
		myModule = new VBox(0);
		myModule.getChildren().addAll(buildToolbar(), myModuleSwitchBox);
		
		myWindow.setContent(myModule);
		
	}
	
	private void switchPalette(Palettes pal){
		myModuleSwitchBox.getChildren().clear();
		String name;		
		
		switch (pal) {
		case COLOR:
			name = pal.getName();
			myModuleSwitchBox.getChildren().add(myColorModule.getInstanceAsNode());
			break;
		case SHAPE:
			name = pal.getName();
			myModuleSwitchBox.getChildren().add(myImageModule.getInstanceAsNode());
			break;
		default:
			name = "";
			break;
		}
		
		mySwitchModulesMenu.setText( name );
		
	}
	
	private Node buildToolbar(){
		HBox toolbar = new HBox(5);
		
		mySwitchModulesMenu = new MenuButton( DEFAULT_PALETTE.getName() );
		
		for(Palettes pal: Palettes.values()){
			MenuItem paletteItem = new MenuItem(pal.getName());
			paletteItem.setOnAction( event -> switchPalette(pal) );
			mySwitchModulesMenu.getItems().add(paletteItem);
		}
		
		toolbar.getChildren().add(mySwitchModulesMenu);
		
		return toolbar;
		
	}
	
	@Override
	public Color getColor(int aColorId) {
		return myColorModule.getColor(aColorId);
	}

	@Override
	public File getFile(int aImageId) {
		return myImageModule.getFile(aImageId);
	}

	@Override
	public void reset() {
		// TODO Finish Reset
		myImageModule.reset();
		myColorModule.reset();
	}

	@Override
	public Node getInstanceAsNode() {
		// TODO Finish getInstanceAsNode
		return myWindow;
	}

	@Override
	public void switchLanguage(Languages aLanguage) {
		myImageModule.switchLanguage(aLanguage);
		myColorModule.switchLanguage(aLanguage);
	}
}
