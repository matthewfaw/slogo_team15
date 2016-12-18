package back_end.model.states.stamps;

import back_end.model.robot.ICloneable;

public interface IStampable<T> {
	
	/**
	 * A method that makes a clone of the cloneable object,
	 * and records the stamp
	 * @param aClonableObject
	 * @return ID of the object cloned
	 */
	public int stamp(ICloneable<T> aCloneableObject);
	
	/**
	 * A method that clears all the currently placed stamps
	 * @return 1 if any stamps were cleared, 0 otherwise
	 */
	public int clearAll();

}
