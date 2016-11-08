package back_end.model.states.background;


import integration.observe.IObservable;

/**
 * Interface used by the front-end to get all information that the back-end held
 * @author hannahfuchshuber
 *
 */

public interface IViewableBackground extends IObservable {
	
	public String getHexadecimalColor(int aIndex);
	
	public int getWidth();

	public int getHeight();
	
	public String getBackgroundColor();

}
