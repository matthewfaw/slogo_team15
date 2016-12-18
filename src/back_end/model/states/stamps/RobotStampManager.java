package back_end.model.states.stamps;

import java.util.ArrayList;
import java.util.List;

import back_end.model.exception.ReflectionException;
import back_end.model.robot.ICloneable;
import back_end.model.robot.IRobot;
import integration.router.IErrorRouter;
import integration.router.IRobotRouter;

public class RobotStampManager implements IStampable<IRobot> {
	private List<IRobot> myStamps;
	private IRobotRouter myRobotRouter;
	private IErrorRouter myErrorRouter;
	
	public RobotStampManager(IRobotRouter aRobotRouter, IErrorRouter aErrorRouter)
	{
		myStamps = new ArrayList<IRobot>();
		myRobotRouter = aRobotRouter;
		myErrorRouter = aErrorRouter;
	}

	@Override
	public int stamp(ICloneable<IRobot> aCloneableObject) {
		IRobot clone;
		try {
			clone = aCloneableObject.cloneThis();
			myStamps.add(clone);
			myRobotRouter.distributeRobot(clone);
			clone.notifyObservers();
			return clone.getImageID();
		} catch (ReflectionException e) {
			myErrorRouter.distributeError(e);
			return -1;
		}
	}

	@Override
	public int clearAll() {
		myStamps.forEach(stamp -> stamp.destroy());
		myStamps = new ArrayList<IRobot>();
		return Math.min(1, myStamps.size());
	}
}
