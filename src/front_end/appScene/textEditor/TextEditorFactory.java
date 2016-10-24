package front_end.appScene.textEditor;

public class TextEditorFactory {

	private TextEditorFactory(){
		// Does Nothing
	}
	
	public static ITextEditor buildTextEditor(int aWidth, int aHeight){
		return new ConcreteTextEditor(aWidth, aHeight);
	}
	
}
