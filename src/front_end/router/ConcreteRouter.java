package front_end.router;

import java.util.ArrayList;
import java.util.Collection;

import back_end.model.robot.IViewRobot;
import front_end.appScene.ApplicationScene;
import integration.observe.IRobotObserver;

/**
 * @author George Bernard
 * Middle Man to accomodate for multiple turtle/variable scenario
 */
class ConcreteRouter implements IRouter {

	Collection<IRobotObserver> myRobotObservers;
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
