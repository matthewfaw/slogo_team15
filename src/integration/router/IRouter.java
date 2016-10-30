package integration.router;

import back_end.model.robot.IViewableRobot;
import back_end.model.states.IViewableVariableState;

public interface IRouter {

	public void distributeRobot(IViewableRobot aViewRobot);
	
	public void distributeVariableMap(IViewableVariableState aViewVariableState);
	
}
