package integration.router;

import java.util.ArrayList;
import java.util.Collection;

import back_end.model.robot.IViewableRobot;
import back_end.model.states.IViewableVariableState;
import back_end.model.states.methodhistory.IViewableUserInputHistory;
import front_end.acceptor.IErrorAcceptor;
import front_end.acceptor.IFunctionAcceptor;
import front_end.acceptor.IHistoryAcceptor;
import front_end.acceptor.IVariableAcceptor;
import front_end.appScene.ApplicationScene;
import front_end.view_modules.IRobotAcceptor;

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
	
	/******* Initializing methods *********/
	
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
	
	/******* API methods *********/
	
	@Override

	public void distributeRobot(IViewableRobot aViewRobot) {
		myRobotAcceptors.forEach( c -> c.giveRobot(aViewRobot) );
	}

	@Override
	public void distributeVariableMap(IViewableVariableState aViewableVariableState) {
		myVariableAcceptors.forEach( c -> c.giveVariables(aViewableVariableState) );
	}

	@Override
	public void distributeHistory(IViewableUserInputHistory aHistory) {
		myHistoryAcceptors.forEach( c -> c.giveHistory(aHistory) );
	}

	@Override
	public void distributeFunction() {
		//myFunctionAcceptors.forEach( c -> c.giveFunction() );		
	}

		
	public void distributeError(Exception aException) {
		myErrorAcceptors.forEach( c -> c.giveError(aException) );
	}

}
