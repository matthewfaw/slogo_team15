package front_end.acceptor;


/**
 * Defines the HistoryAcceptor Property. 
 * 
 * Implemented by any module that needs to have 
 * History pushed to it. 
 * 
 * @author George Bernard
 */
public interface IHistoryAcceptor {

	/**
	 * Pushes specified History to robots
	 */
	public void giveHistory(String aHistory);
	
}
