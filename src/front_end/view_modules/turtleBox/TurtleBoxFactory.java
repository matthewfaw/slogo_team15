package front_end.view_modules.turtleBox;

public class TurtleBoxFactory {

    private TurtleBoxFactory () {
        // Does Nothing
    }

    public static ITurtleBox buildTurtleBox (int aWidth, int aHeight) {
        return new ConcreteTurtleBox(aWidth, aHeight);
    }
}
