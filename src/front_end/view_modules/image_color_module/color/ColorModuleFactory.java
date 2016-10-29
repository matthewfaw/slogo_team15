package front_end.view_modules.image_color_module.color;

import front_end.view_modules.image_color_module.interfaces.IColorModule;

public class ColorModuleFactory {

	private ColorModuleFactory(){
		// Does Nothing
	}
	
	public static IColorModule build(){
		return new ConcreteColorModule();
	}
}
