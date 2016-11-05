package front_end.acceptor;

import back_end.model.states.methodhistory.IViewableUserInputHistory;

/**
 * Defines the HistoryAcceptor Property. 
 * 
 * Implemented by any module that needs to have 
 * updating History pushed to it. 
 * 
 * @author George Bernard
 */
public interface IHistoryAcceptor {

	/**
	 * Pushes specified History to robots
	 */

	public void giveHistory(IViewableUserInputHistory aHistory);
	
}
