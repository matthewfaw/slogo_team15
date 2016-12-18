package back_end.model.robot;

import back_end.model.exception.ReflectionException;

public interface ICloneable<T> {
	
	/**
	 * Creates a clone of this object
	 * 
	 * @return a new instance of the object of type T, with the same field values
	 */
	public T cloneThis() throws ReflectionException;

}
