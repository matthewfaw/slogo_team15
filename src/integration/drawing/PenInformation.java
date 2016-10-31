package integration.drawing;

import integration.observe.Observable;

public class PenInformation extends Observable {

	private LineStyleSpec myLineStyle;
	private int myPenThickness;
	private int myColorID;
	private boolean myPenUp;
	
	public PenInformation(){}
	
	/**SETTERS**/
	
	public void setLineStyle(LineStyleSpec aLineStyle) {
		myLineStyle = aLineStyle;
	}
	
	public void setPenThickness(int aPenThickness) {
		myPenThickness = aPenThickness;
	}
	
	public void setColorID(int aColorID) {
		myColorID = aColorID;
	}
	
	public void setPenUp(boolean aPenUp) {
		myPenUp = aPenUp;
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
