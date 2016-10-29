package front_end.router;

import front_end.appScene.ApplicationScene;

public class RouterFactory {

	private RouterFactory(){
		// Does Nothing
	}
	
	public static IRouter build(ApplicationScene aAppScene){
		return new ConcreteRouter(aAppScene);
	}
	
}
