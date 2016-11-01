package front_end.view_modules.errorViewer;

import front_end.view_modules.textEditor.ITextEditor;


public class ErrorViewerFactory {
    private ErrorViewerFactory () {
        // Does Nothing
    }

    public static IErrorViewer build (int aWidth, int aHeight, ITextEditor aTextEditor) {
        return new ConcreteErrorViewer(aWidth, aHeight, aTextEditor);
    }
}
