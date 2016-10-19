package appScene.errorViewer;

public class ErrorViewerFactory {
	private ErrorViewerFactory(){
		//Does Nothing
	}	
	
	public static IErrorViewer buildErrorViewer(int aWidth, int aHeight){
		return new ConcreteErrorViewer(aWidth, aHeight);
	}
}
