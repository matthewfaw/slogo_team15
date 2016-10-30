package integration.drawing;

import integration.observe.IObservable;

public interface IViewablePen extends IObservable {
	
	public LineStyleSpec getLineStyle();
	
	public int getPenThickness();
	
	public int getColorID();
	
	public boolean isPenUp();

}
