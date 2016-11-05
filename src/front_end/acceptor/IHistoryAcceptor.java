package front_end.acceptor;

import back_end.model.states.methodhistory.IViewableUserInputHistory;

/**
 * Defines the HistoryAcceptor Property. 
 * 
 * Implemented by any module that needs to have 
 * updating History pushed to it. 
 * 
 * @see front_end.acceptor
 * @author George Bernard
 */
public interface IHistoryAcceptor {

	/**
	 * Pushes the ColorSender to an observer.
	 * 
	 * @see back_end.model.states.methodhistory.IViewableUserInputHistory
	 * @param aHistory
	 */
	public void giveHistory(IViewableUserInputHistory aHistory);
	
}
