package front_end.router;

import back_end.model.robot.IViewRobot;

public interface IRouter {

	public void distributeRobot(IViewRobot aViewRobot);
	
	public void distributeVariableMap();
	
}
