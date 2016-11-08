package front_end.acceptor;

import back_end.model.states.background.IViewableColorPalette;

/**
 * Defines the interface for accepting the updating color palette
 *  
 * @see front_end.acceptor
 * @author George Bernard
 */
public interface IColorPaletteAcceptor {
	
	/**
	 * Pushes the Observable Color Palette state to this object.
	 * 
	 * @see back_end.model.states.background.IViewableColorPalette
	 * @param aViewPalette
	 */
	void giveColorPalette(IViewableColorPalette aViewPalette );
}
