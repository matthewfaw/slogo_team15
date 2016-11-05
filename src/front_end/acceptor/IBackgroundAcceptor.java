package front_end.acceptor;

import back_end.model.states.background.IViewableBackground;

/**
 * Defines the interface for accepting background state
 * 
 * @author George Bernard
 */
public interface IBackgroundAcceptor {

	/**
	 * Pushes the Observable Background state to an observer.
	 * 
	 * @param aViewBackground
	 */
	public void giveBackground(IViewableBackground aViewBackground);
	
}
