package back_end.model.states.background;

/**
 * Interface used by the back-end to update the background colors
 * @author hannahfuchshuber
 *
 */

public interface IModifiableBackground {
	
	public String getBackgroundColor();
	
	public void setBackgroundColor(int myColor);

}
