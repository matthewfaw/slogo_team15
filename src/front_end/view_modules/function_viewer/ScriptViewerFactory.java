package front_end.view_modules.function_viewer;

public class ScriptViewerFactory {

    private ScriptViewerFactory () {
        // Does Nothing
    }

    public static IScriptViewer buildViewerFactory (int aWidth, int aHeight) {
        return new ConcreteScriptViewer(aWidth, aHeight);
    }

}
