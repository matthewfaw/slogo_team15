package integration.drawing;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import integration.observe.AbstractObservable;

public class PenInformation extends AbstractObservable {
	
	private static final String DEFAULT = "resources.defaultvalues.DefaultValues";

    private ResourceBundle myDefaultValues;
	private LineStyleSpec myLineStyle;
	private int myPenThickness;
	private int myColorID;
	private boolean myPenUp;
	
	public PenInformation(){
		myDefaultValues = PropertyResourceBundle.getBundle(DEFAULT);
		myPenUp = Boolean.parseBoolean(myDefaultValues.getString("PenUp"));
		myLineStyle = LineStyleSpec.SOLID;
		myPenThickness = Integer.parseInt(myDefaultValues.getString("PenThickness"));
		myColorID = Integer.parseInt(myDefaultValues.getString("ColorID"));
	}
	
	/**SETTERS**/
	
	public void setLineStyle(LineStyleSpec aLineStyle) {
		myLineStyle = aLineStyle;
		notifyObservers();
	}
	
	public void setPenThickness(int aPenThickness) {
		myPenThickness = aPenThickness;
		notifyObservers();
	}
	
	public void setColorID(int aColorID) {
		myColorID = aColorID;
		notifyObservers();
	}
	
	public void setPenUp(boolean aPenUp) {
		myPenUp = aPenUp;
		notifyObservers();
	}
	
	
	/**GETTERS**/
	
	public LineStyleSpec getLineStyle() {
		return myLineStyle;
	}
	
	public int getPenThickness() {
		return myPenThickness;
	}
	
	public int getColorID() {
		return myColorID;
	}
	
	public boolean isPenUp() {
		return myPenUp; 
	}
	
}
