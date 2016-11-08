package integration.router;

import java.util.ArrayList;
import java.util.Collection;

import back_end.model.exception.IExceptionDebugger;
import back_end.model.robot.IViewableRobot;
import back_end.model.states.IViewableMethodState;
import back_end.model.states.IViewableVariableState;
import back_end.model.states.background.IViewableBackground;
import back_end.model.states.background.IViewableColorPalette;
import back_end.model.states.methodhistory.IViewableUserInputHistory;
import front_end.acceptor.IBackgroundAcceptor;
import front_end.acceptor.IColorPaletteAcceptor;
import front_end.acceptor.IColorSenderAcceptor;
import front_end.acceptor.IErrorAcceptor;
import front_end.acceptor.IFunctionAcceptor;
import front_end.acceptor.IHistoryAcceptor;
import front_end.acceptor.IRobotAcceptor;
import front_end.acceptor.IRobotSenderAcceptor;
import front_end.acceptor.IVariableAcceptor;
import front_end.appScene.ApplicationScene;
import front_end.sender.IColorSender;
import front_end.sender.IRobotSender;

/** 
 * @see IRouter
 * @author George Bernard
 */
class ConcreteRouter implements IRouter {

	
	private Collection<IRobotAcceptor> myRobotAcceptors;
	private Collection<IVariableAcceptor> myVariableAcceptors;
	private Collection<IErrorAcceptor> myErrorAcceptors;
	private Collection<IFunctionAcceptor> myFunctionAcceptors;
	private Collection<IHistoryAcceptor> myHistoryAcceptors;
	private Collection<IBackgroundAcceptor> myBackgroundAcceptors;
	private Collection<IColorPaletteAcceptor> myColorAcceptors;
	
	private Collection<IColorSenderAcceptor> myColorSendAcceptors;
	private Collection<IRobotSenderAcceptor> myRobotSendAcceptors;

	ApplicationScene myAppScene;
	
	/******* Initializing methods *********/
	
	ConcreteRouter(ApplicationScene aAppScene){
		myAppScene = aAppScene;
		
		setRobotAcceptors();
		setErrorAcceptors();
		setVariableAcceptors();
		setHistoryAcceptors();
		setFunctionAcceptors();
		setBackgroundAcceptors();
		setColorPaletteAcceptors();
		setColorSenderAcceptors();
		setRobotSenderAcceptors();
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
	
	private void setBackgroundAcceptors(){
		myBackgroundAcceptors = new ArrayList<>();
		myBackgroundAcceptors.add(myAppScene.getMyTurtleBox());
	}
	
	private void setColorPaletteAcceptors() {
		myColorAcceptors = new ArrayList<>();
		myColorAcceptors.add(myAppScene.getMyShapeColorModule());
	}
	
	private void setColorSenderAcceptors() {
		myColorSendAcceptors = new ArrayList<>();
		myColorSendAcceptors.add(myAppScene.getMyTurtleBox());
		myColorSendAcceptors.add(myAppScene.getMyShapeColorModule());
		}
	
	private void setRobotSenderAcceptors() {
		myRobotSendAcceptors = new ArrayList<>();
		myRobotSendAcceptors.add(myAppScene.getMyStatesBox());
	}

	//private void setMethodAcceptors() {
	//    myMethodAcceptors = new ArrayList<>();
	//    myMethodAcceptors.add();
	//}
	
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
	public void distributeFunction(IViewableMethodState aMethod) {
		myFunctionAcceptors.forEach( c -> c.giveFunction(aMethod) );		
	}

		
	public void distributeError(IExceptionDebugger aException) {
		myErrorAcceptors.forEach( c -> c.giveError(aException) );
	}

	@Override
	public void distributeBackground(IViewableBackground aViewBackground) {
		myBackgroundAcceptors.forEach( c -> c.giveBackground(aViewBackground) );
	}

	@Override
	public void distributeColorPalette(IViewableColorPalette aViewColorPalette) {
		myColorAcceptors.forEach(c -> c.giveColorPalette(aViewColorPalette));
	}

	@Override
	public void distributeRobotSenders(IRobotSender aRoboSender) {
		myRobotSendAcceptors.forEach( c -> c.giveRobotSender(aRoboSender));
	}

	@Override
	public void distributeColorSenders(IColorSender aColorSender) {
		myColorSendAcceptors.forEach( c -> c.giveColorSender(aColorSender) );	
	}

	@Override
    public void distributeMethods (IViewableMethodState aViewableMethods) {
        //myMethodAcceptors.forEach(c -> c.giveMethodState(aViewableMethods));
        
    }

}
