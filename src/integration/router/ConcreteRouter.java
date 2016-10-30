package integration.router;

import java.util.ArrayList;
import java.util.Collection;

import back_end.model.robot.IViewableRobot;
import back_end.model.states.IViewableVariableState;
import front_end.appScene.ApplicationScene;
import front_end.view_modules.IRobotAcceptor;
import front_end.view_modules.IViewVariableAcceptor;
import integration.observe.IObserver;

/**
 * @author George Bernard
 * Middle Man to accomodate for multiple turtle/variable scenario
 */
class ConcreteRouter implements IRouter {

	Collection<IRobotAcceptor> myRobotAcceptors;
	Collection<IViewVariableAcceptor> myVariableObservers;
	ApplicationScene myAppScene;
	
	ConcreteRouter(ApplicationScene aAppScene){
		myRobotAcceptors = new ArrayList<>();
		myAppScene = aAppScene;
		setRobotObservers();
	}

	private void setRobotObservers(){
		myRobotAcceptors.add( myAppScene.getMyTurtleBox() );
		myRobotAcceptors.add( myAppScene.getMyStatesBox() );
	}
	
	@Override
	public void distributeRobot(IViewableRobot aViewRobot) {
		myRobotAcceptors.forEach( ro -> ro.giveRobot(aViewRobot) );
	}

	@Override
	public void distributeVariableMap(IViewableVariableState aViewVariableState) {
		myVariableObservers.forEach( vo -> vo.giveVariableState(aViewVariableState) );				
	}

}
