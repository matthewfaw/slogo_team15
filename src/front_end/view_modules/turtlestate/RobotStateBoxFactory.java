package front_end.view_modules.turtlestate;

import front_end.view_modules.image_color_module.interfaces.IImageColorModule;

/**
 * Made to hide instantiation of this module from anything outside the package.
 * 
 * @author George Bernard
 */
public class RobotStateBoxFactory {

	private RobotStateBoxFactory(){
		// Does Nothing
	}
	
	public static IAllRobotsStateBox build(IImageColorModule aImageColorMap){
		return new ConcreteAllRobotsStateBox(aImageColorMap);
	}
	
	public static IAllRobotsStateBox build(int aWidth, int aHeight, IImageColorModule aImageColorMap){
		return new ConcreteAllRobotsStateBox(aWidth, aHeight, aImageColorMap);
	}
	
}
