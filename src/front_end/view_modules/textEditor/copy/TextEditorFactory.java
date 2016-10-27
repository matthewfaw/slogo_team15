package front_end.view_modules.textEditor.copy;

public class TextEditorFactory {

    private TextEditorFactory () {
        // Does Nothing
    }

    public static ITextEditor buildTextEditor (int aWidth, int aHeight) {
        return new ConcreteTextEditor(aWidth, aHeight);
    }

}
