package integration.router;

import java.util.ArrayList;
import java.util.Collection;

import back_end.model.robot.IViewableRobot;
import back_end.model.states.IViewableVariableState;
import front_end.appScene.ApplicationScene;
import integration.acceptors.IRobotAcceptor;
import integration.acceptors.IVariableAcceptor;
import integration.observe.IObserver;

/**
 * @author George Bernard
 * Middle Man to accomodate for multiple turtle/variable scenario
 */
class ConcreteRouter implements IRouter {

	Collection<IRobotAcceptor> myRobotAcceptors;
	Collection<IVariableAcceptor> myVariableAcceptors;
	ApplicationScene myAppScene;
	
	ConcreteRouter(ApplicationScene aAppScene){
		myRobotAcceptors = new ArrayList<>();
		myVariableAcceptors = new ArrayList<>();
		myAppScene = aAppScene;
		setRobotAcceptors();
		setVariableAcceptors();
	}

	private void setRobotAcceptors(){
		myRobotAcceptors.add( myAppScene.getMyTurtleBox() );
		myRobotAcceptors.add( myAppScene.getMyStatesBox() );
	}
	
	private void setVariableAcceptors(){
		myVariableAcceptors.add( myAppScene.getMyVariableViewer() );
	}
	
	@Override
	public void distributeRobot(IViewableRobot aViewRobot) {
		myRobotAcceptors.forEach( ro -> ro.giveRobot(aViewRobot) );
	}

		
	@Override
	public void distributeVariableMap(IViewableVariableState aViewVariableState) {
		myVariableAcceptors.forEach( vo -> vo.giveVariableState(aViewVariableState) );				
	}

}
