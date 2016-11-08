package back_end.model.states.background;

import java.util.Collection;

import integration.observe.IObservable;

/**
 * Interface to the front-end to get information about the Color Palette
 * @author hannahfuchshuber
 *
 */

public interface IViewableColorPalette extends IObservable{
	
	public Collection<Integer> getPaletteColors();
	
	public String getHexadecimalColor(int aIndex);

}
