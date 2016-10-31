package front_end.view_modules.turtleBox;

import front_end.view_modules.image_color_module.interfaces.IImageColorModule;

public class TurtleBoxFactory {

    private TurtleBoxFactory () {
        // Does Nothing
    }

    public static ITurtleBox buildTurtleBox (int aWidth, int aHeight, IImageColorModule aShapeColorMap) {
        return new ConcreteTurtleBox(aWidth, aHeight, aShapeColorMap);
    }
}
