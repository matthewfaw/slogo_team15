package front_end.acceptor;

import front_end.sender.IColorSender;

/**
 * Defines the interface for accepting the Color Sender
 * 
 * @see front_end.acceptor
 * @author George Bernard
 */
public interface IColorSenderAcceptor {

	/**
	 * Pushes the ColorSender to this object.
	 * 
	 * @see front_end.sender.IColorSender
	 * @param aColorSender
	 */
	public void giveColorSender(IColorSender aColorSender);
	
}
