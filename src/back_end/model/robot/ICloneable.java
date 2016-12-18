package back_end.model.robot;

public interface ICloneable<T> {
	
	/**
	 * Creates a clone of this object
	 * 
	 * @return a new instance of the object of type T, with the same field values
	 */
	public T clone();

}
