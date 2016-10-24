package front_end.appScene.toolbar;

public class ToolbarFactory {

	private ToolbarFactory(){
		// Does Nothing
	}
	
	public static IToolbar buildToolbar(int aWidth, int aHeight){
		return new ConcreteToolbar(aWidth, aHeight);
	}
	
	
}
