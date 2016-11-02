package back_end.model.states.background;

import java.util.Collection;

import integration.observe.IObservable;

public interface IViewableColorPalette extends IObservable{
	
	public Collection<Integer> getPaletteColors();
	
	public String getHexadecimalColor(int aIndex);

}
