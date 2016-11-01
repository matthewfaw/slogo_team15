package back_end.model.states.background;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import integration.observe.Observable;

public class BackgroundInformation extends Observable implements IViewableBackground, IModifiableBackground {
	
	private static final String DEFAULT = "resources.defaultvalues.DefaultValues";
	
	private ResourceBundle myDefaultValues;
	private int myBackgroundColor;
	private Map<Integer, String> myPaletteColors;
	private int myWidth;
	private int myHeight; 
	
	public BackgroundInformation() {
		myDefaultValues = PropertyResourceBundle.getBundle(DEFAULT);
		myWidth = Integer.parseInt(myDefaultValues.getString("Width"));
		myHeight = Integer.parseInt(myDefaultValues.getString("Height")); 
		myBackgroundColor = Integer.parseInt(myDefaultValues.getString("BackgroundColor")); 
		myPaletteColors = new HashMap<Integer, String>();
	}
	
	/**GETTERS**/
	
	public Collection<Integer> getPaletteColors() {
		return myPaletteColors.keySet();
	}
	
	public String getHexadecimalColor(int aIndex) {
		return myPaletteColors.get(aIndex);
	}
	
	public int getWidth() {
		return myWidth;
	}

	public int getHeight() {
		return myHeight;
	}
	
	public int getBackgroundColor() {
		return myBackgroundColor;
	}
	
	/**SETTERS**/
	
	public void setPaletteColor(int aIndex, int aRed, int aBlue, int aGreen) {
		String hex = Integer.toHexString(aRed) + Integer.toHexString(aBlue) + Integer.toHexString(aGreen);
		myPaletteColors.put(aIndex, hex);
	}
	
	public void setBackgroundColor(int myColor) {
		myBackgroundColor = myColor;
	}
	
	public void setHeight(int aHeight) {
		myHeight = aHeight;
	}
	
	public void setWidth(int aWidth) {
		myWidth = aWidth;
	}

}
