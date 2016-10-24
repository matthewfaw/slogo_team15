package appScene.errorViewer;

import appScene.textEditor.ITextEditor;

public class ErrorViewerFactory {
	private ErrorViewerFactory(){
		//Does Nothing
	}	
	
	public static IErrorViewer buildErrorViewer(int aWidth, int aHeight, ITextEditor aTextEditor){
		return new ConcreteErrorViewer(aWidth, aHeight, aTextEditor);
	}
}
