package integration.router;

import back_end.model.robot.IViewRobot;

public interface IRouter {

	public void distributeRobot(IViewRobot aViewRobot);
	
	public void distributeVariableMap();
	
}
