package front_end.view_modules.shape_color_module;

import front_end.view_modules.shape_color_module.interfaces.IShapeColorModule;

public class ShapeColorModuleFactory {

	private ShapeColorModuleFactory(){
		// Does Nothing
	}
	
	public static IShapeColorModule build(){
		return new ConcreteShapeColorModule();
	}
	
}
