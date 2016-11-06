package front_end.view_modules.variableViewer;

/**
 * Variable Viewer Factory 
 * 
 * @author George Bernard
 */
public class VariableViewerFactory {

    private VariableViewerFactory () {
        // Does Nothing
    }

    /**
     * Builds a new instance of the Variable Viewer
     * 
     * @param aWidth
     * @param aHeight
     * @return new instance of VariableViewer
     */
    public static IVariableViewer build (int aWidth, int aHeight) {
        return new ConcreteVariableViewer(aWidth, aHeight);
    }

}
