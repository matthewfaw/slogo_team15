package front_end.view_modules.function_viewer;

public class FunctionViewerFactory {

    private FunctionViewerFactory () {
        // Does Nothing
    }

    public static IFunctionViewer build (int aWidth, int aHeight) {
        return new ConcreteFunctionViewer(aWidth, aHeight);
    }

}
