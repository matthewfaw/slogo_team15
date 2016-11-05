package front_end.acceptor;

import front_end.sender.IColorSender;

/**
 * Defines the interface for accepting the Color Sender
 * 
 * @see front_end.sender.IColorSender
 * @author George Bernard
 */
public interface IColorSenderAcceptor {

	public void giveColorSender(IColorSender aColorSender);
	
}
