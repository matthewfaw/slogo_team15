package front_end.view_modules.textEditor;

public class TextEditorFactory {

    private TextEditorFactory () {
        // Does Nothing
    }

    public static ITextEditor build (int aWidth, int aHeight) {
        return new ConcreteTextEditor(aWidth, aHeight);
    }

}
