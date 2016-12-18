package back_end.model.states.stamps;

import java.util.ArrayList;
import java.util.List;

import back_end.model.robot.ICloneable;
import back_end.model.robot.IRobot;
import integration.router.IRobotRouter;

public class RobotStampManager implements IStampable<IRobot> {
	private List<IRobot> myStamps;
	private IRobotRouter myRouter;
	
	public RobotStampManager(IRobotRouter aRouter)
	{
		myStamps = new ArrayList<IRobot>();
		myRouter = aRouter;
	}

	@Override
	public int stamp(ICloneable<IRobot> aCloneableObject) {
		IRobot clone = aCloneableObject.clone();
		myStamps.add(clone);
		myRouter.distributeRobot(clone);
		clone.notifyObservers();
		return clone.getImageID();
	}

	@Override
	public int clearAll() {
		myStamps.forEach(stamp -> stamp.destroy());
		myStamps = new ArrayList<IRobot>();
		return Math.min(1, myStamps.size());
	}
}
