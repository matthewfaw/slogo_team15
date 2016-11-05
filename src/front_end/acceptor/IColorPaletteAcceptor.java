package front_end.acceptor;

import back_end.model.states.background.IViewableColorPalette;

/**
 * Defines the interface for accepting the updating color palette
 *  
 * @author George Bernard
 */
public interface IColorPaletteAcceptor {
	void giveColorPalette(IViewableColorPalette aViewPalette );
}
