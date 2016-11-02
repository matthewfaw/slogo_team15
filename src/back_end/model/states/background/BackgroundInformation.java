package back_end.model.states.background;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import integration.observe.Observable;

public class BackgroundInformation extends Observable implements IViewableBackground, IModifiableBackground, IViewableColorPalette {
	
	private static final String DEFAULT_PACKAGE = "resources.defaultvalues.";
	private static final String DEFAULT = "DefaultValues";
	private static final String COLORS = "DefaultColors";
	
	private ResourceBundle myDefaultValues;
	private ResourceBundle myDefaultColors;
	private String myBackgroundColor;
	private Map<Integer, String> myPaletteColors;
	private int myWidth;
	private int myHeight; 
	
	public BackgroundInformation() {
		myDefaultValues = PropertyResourceBundle.getBundle(DEFAULT_PACKAGE + DEFAULT);
		myDefaultColors = PropertyResourceBundle.getBundle(DEFAULT_PACKAGE + COLORS);
		myWidth = Integer.parseInt(myDefaultValues.getString("Width"));
		myHeight = Integer.parseInt(myDefaultValues.getString("Height")); 
		myPaletteColors = new HashMap<Integer, String>();
		initalizePaletteColors();
		myBackgroundColor = myPaletteColors.get(Integer.parseInt(myDefaultValues.getString("BackgroundColor"))); 
	}
	
	private void initalizePaletteColors() {
		Enumeration<String> iter = myDefaultColors.getKeys();
		int colorNumber = 0;
        while (iter.hasMoreElements()) {
        	myPaletteColors.put(colorNumber++, new String(myDefaultColors.getString(iter.nextElement())));
        }
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
	
	public String getBackgroundColor() {
		return myBackgroundColor;
	}
	
	/**SETTERS**/
	
	public void setPaletteColor(int aIndex, int aRed, int aBlue, int aGreen) {
		String hex = Integer.toHexString(aRed) + Integer.toHexString(aBlue) + Integer.toHexString(aGreen);
		myPaletteColors.put(aIndex, hex);
		notifyObservers();
	}
	
	public void setBackgroundColor(int aColorIndex) {
		myBackgroundColor = myPaletteColors.get(myBackgroundColor);
		notifyObservers();
	}
	
	public void setPaletteColor(int aIndex, String aHex) {
		myPaletteColors.put(aIndex, aHex);
		notifyObservers();
	}
	
	public void setBackgroundColor(String aHex) {
		myBackgroundColor = aHex;
	}

}
