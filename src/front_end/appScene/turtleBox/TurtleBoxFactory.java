package front_end.appScene.turtleBox;

public class TurtleBoxFactory {

    private TurtleBoxFactory () {
        // Does Nothing
    }

    public static ITurtleBox buildTurtleBox (int aWidth, int aHeight) {
        return new ConcreteTurtleBox(aWidth, aHeight);
    }
}
