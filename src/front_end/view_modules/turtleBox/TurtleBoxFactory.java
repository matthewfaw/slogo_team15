package front_end.view_modules.turtleBox;

import front_end.view_modules.shape_color_module.interfaces.IShapeColorModule;

public class TurtleBoxFactory {

    private TurtleBoxFactory () {
        // Does Nothing
    }

    public static ITurtleBox buildTurtleBox (int aWidth, int aHeight, IShapeColorModule aShapeColorMap) {
        return new ConcreteTurtleBox(aWidth, aHeight, aShapeColorMap);
    }
}
