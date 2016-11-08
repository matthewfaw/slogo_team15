package front_end.acceptor;

import back_end.model.states.background.IViewableBackground;

/**
 * Defines the interface for accepting background state
 * 
 * @see front_end.acceptor
 * @author George Bernard
 */
public interface IBackgroundAcceptor {

	/**
	 * Pushes the Observable Background state to this object.
	 * 
	 * @see back_end.model.states.background.IViewableBackground

	 * @param aViewBackground
	 */
	public void giveBackground(IViewableBackground aViewBackground);
	
}
