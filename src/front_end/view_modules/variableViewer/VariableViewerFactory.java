package front_end.view_modules.variableViewer;

public class VariableViewerFactory {

    private VariableViewerFactory () {
        // Does Nothing
    }

    public static IVariableViewer build (int aWidth, int aHeight) {
        return new ConcreteVariableViewer(aWidth, aHeight);
    }

}
