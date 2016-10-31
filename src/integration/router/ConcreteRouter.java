package integration.router;

import java.util.ArrayList;
import java.util.Collection;

import back_end.model.robot.IViewRobot;
import back_end.model.states.IViewVariableState;
import front_end.acceptor.*;
import front_end.appScene.ApplicationScene;

/**
 * Middle Man to accommodate for multiple turtle/variable scenario
 * 
 * @author George Bernard
 */
class ConcreteRouter implements IRouter {

	private Collection<IRobotAcceptor> myRobotAcceptors;
	private Collection<IVariableAcceptor> myVariableAcceptors;
	private Collection<IErrorAcceptor> myErrorAcceptors;
	private Collection<IFunctionAcceptor> myFunctionAcceptors;
	private Collection<IHistoryAcceptor> myHistoryAcceptors;
	ApplicationScene myAppScene;
	
	ConcreteRouter(ApplicationScene aAppScene){
		myAppScene = aAppScene;
		
		setRobotAcceptors();
		setErrorAcceptors();
		setVariableAcceptors();
		setHistoryAcceptors();
		setFunctionAcceptors();
		

	}
	
	private void setRobotAcceptors(){
		myRobotAcceptors = new ArrayList<>();
		myRobotAcceptors.add( myAppScene.getMyTurtleBox() );
		myRobotAcceptors.add( myAppScene.getMyStatesBox() );
	}
	
	private void setVariableAcceptors(){
		myVariableAcceptors = new ArrayList<>();
		myVariableAcceptors.add( myAppScene.getMyVariableViewer() );
	}
	
	private void setErrorAcceptors(){
		myErrorAcceptors = new ArrayList<>();
		myErrorAcceptors.add( myAppScene.getMyErrorViewer() );
	}
	
	private void setFunctionAcceptors(){
		myFunctionAcceptors = new ArrayList<>();
		myFunctionAcceptors.add( myAppScene.getMyFunctionViewer() );
	}
	
	private void setHistoryAcceptors(){
		myHistoryAcceptors = new ArrayList<>();
		myHistoryAcceptors.add( myAppScene.getMyHistoryModule() );
	}
	
	@Override
	public void distributeRobot(IViewRobot aViewRobot) {
		myRobotAcceptors.forEach( c -> c.giveRobot(aViewRobot) );
	}

	@Override
	public void distributeVariableMap(IViewVariableState aViewVariableState) {
		myVariableAcceptors.forEach( c -> c.giveVariables(aViewVariableState) );
	}

	@Override
	public void distributeHistory() {
		myHistoryAcceptors.forEach( c -> c.giveHistory() );
	}

	@Override
	public void distributeFunction() {
		//myFunctionAcceptors.forEach( c -> c.giveFunction() );		
	}

	@Override
	public void distributeError(Exception aException) {
		myErrorAcceptors.forEach( c -> c.giveError(aException) );
	}

}
