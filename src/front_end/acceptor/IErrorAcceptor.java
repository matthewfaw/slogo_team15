package front_end.acceptor;

/**
 * Defines the ErrorAcceptor Property. 
 * 
 * Implemented by any module that needs to have 
 * Error Objects pushed to it. 
 *  
 * @author George Bernard
 */
public interface IErrorAcceptor {

	public void giveError(Exception aException);
	
}
