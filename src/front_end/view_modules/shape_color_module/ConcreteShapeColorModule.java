package front_end.view_modules.shape_color_module;

import front_end.view_modules.shape_color_module.interfaces.IColorModule;
import front_end.view_modules.shape_color_module.interfaces.IShapeColorModule;
import front_end.view_modules.shape_color_module.interfaces.IShapeModule;
import integration.languages.Languages;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.paint.Color;

class ConcreteShapeColorModule implements IShapeColorModule {

	private ScrollPane myWindow;
	
	private IColorModule myColorModule;
	private IShapeModule myShapeModule;
	
	ConcreteShapeColorModule(){
		myColorModule = new ConcreteColorModule();
		myShapeModule = new ConcreteShapeModule();
		
		myWindow = new ScrollPane();
		myWindow.setHbarPolicy( ScrollBarPolicy.NEVER );
		myWindow.setVbarPolicy( ScrollBarPolicy.ALWAYS );
		
		//TODO Change this
		myWindow.setContent(myColorModule.getInstanceAsNode());
		
	}
	
	@Override
	public Color getColor(int aColorId) {
		return myColorModule.getColor(aColorId);
	}

	@Override
	public String getFilename(int aImageId) {
		return myShapeModule.getFilename(aImageId);
	}

	@Override
	public void reset() {
		// TODO Finish Reset
		myShapeModule.reset();
		myColorModule.reset();
	}

	@Override
	public Node getInstanceAsNode() {
		// TODO Finish getInstanceAsNode
		return myWindow;
	}

	@Override
	public void switchLanguage(Languages aLanguage) {
		myShapeModule.switchLanguage(aLanguage);
		myColorModule.switchLanguage(aLanguage);
	}
}
