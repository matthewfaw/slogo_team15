package back_end.model.states.background;

import java.util.Collection;

import integration.observe.IObservable;

public interface IViewableBackground extends IObservable {
	
	public Collection<Integer> getPaletteColors();
	
	public String getHexadecimalColor(int aIndex);
	
	public int getWidth();

	public int getHeight();
	
	public int getBackgroundColor();

}
