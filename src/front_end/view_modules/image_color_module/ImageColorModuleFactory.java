package front_end.view_modules.image_color_module;

import front_end.view_modules.image_color_module.interfaces.IImageColorModule;

public class ImageColorModuleFactory {

	private ImageColorModuleFactory(){
		// Does Nothing
	}
	
	public static IImageColorModule build(){
		return new ConcreteImageColorModule();
	}
	
}
