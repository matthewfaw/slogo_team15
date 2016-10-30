package integration.router;

import java.util.ArrayList;
import java.util.Collection;

import back_end.model.robot.IViewRobot;
import front_end.appScene.ApplicationScene;
import front_end.view_modules.IRobotAcceptor;
import integration.observe.IObserver;

/**
 * @author George Bernard
 * Middle Man to accommodate for multiple turtle/variable scenario
 */
class ConcreteRouter implements IRouter {

	Collection<IRobotAcceptor> myRobotObservers;
	Collection<Object> myVariableObservers;
	ApplicationScene myAppScene;
	
	ConcreteRouter(ApplicationScene aAppScene){
		myRobotObservers = new ArrayList<>();
		myAppScene = aAppScene;
		setRobotObservers();
	}

	private void setRobotObservers(){
		myRobotObservers.add( myAppScene.getMyTurtleBox() );
		myRobotObservers.add( myAppScene.getMyStatesBox() );
	}
	
	@Override
	public void distributeRobot(IViewRobot aViewRobot) {
		myRobotObservers.forEach( ro -> ro.giveRobot(aViewRobot) );
	}

	@Override
	public void distributeVariableMap() {
		myVariableObservers.forEach( vo -> {});
	}

}
