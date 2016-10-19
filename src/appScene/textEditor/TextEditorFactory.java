package appScene.textEditor;

public class TextEditorFactory {

	private TextEditorFactory(){
		// Does Nothing
	}
	
	public static ITextEditor createTextEditor(int aWidth, int aHeight){
		return new ConcreteTextEditor(aWidth, aHeight);
	}
	
}
