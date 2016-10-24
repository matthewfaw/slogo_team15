package appScene.variableViewer;

public class VariableViewerFactory {

	private VariableViewerFactory(){
		//Does Nothing
	}
	
	public static IVariableViewer buildVariableViewer(int aWidth, int aHeight){
		return new ConcreteVariableViewer(aWidth, aHeight);
	}
	
}
