package front_end.appScene.scriptViewer;

public class ScriptViewerFactory {

    private ScriptViewerFactory () {
        // Does Nothing
    }

    public static IScriptViewer buildViewerFactory (int aWidth, int aHeight) {
        return new ConcreteScriptViewer(aWidth, aHeight);
    }

}
