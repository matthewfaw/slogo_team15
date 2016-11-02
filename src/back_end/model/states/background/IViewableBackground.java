package back_end.model.states.background;


import integration.observe.IObservable;

public interface IViewableBackground extends IObservable {
	
	public String getHexadecimalColor(int aIndex);
	
	public int getWidth();

	public int getHeight();
	
	public String getBackgroundColor();

}
