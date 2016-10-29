package front_end.view_modules.image_color_module.image;

import front_end.view_modules.image_color_module.interfaces.IImageModule;

public class ImageModuleFactory {

	private ImageModuleFactory(){
		// Does Nothing
	}
	
	public static IImageModule build(){
		return new ConcreteImageModule();
	}
	
}
