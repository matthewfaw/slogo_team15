package front_end.sender;

/**
 * This interface delineates everything the front_end needs 
 * to tell the backend to either change or be updated with 
 * apropos of colors
 * 
 * @see front_end.sender
 * @author George Bernard
 */
public interface IColorSender {

	/**
	 * Creates a new color in the palette with 
	 * RGB HEX value passed as string 
	 * 
	 * @param aRGB
	 */
	public void newColor(String aRGB);
	
	/**
	 * Sets the Hex value of a color in the palette
	 * 
	 * @param aColorID
	 * @param aRGB
	 */
	public void setColor(int aColorID, String aRGB);
	
	/**
	 * Sets the background color to an indexed color
	 * in the color palette
	 * 
	 * @param aColorID
	 */
	public void setBackground(int aColorID);
 	
	
 	/**
 	 * Sets the background color to that of a new color
 	 * specified by the HEX string given.
 	 * 
 	 * @param RGB
 	 */
 	public void setBackground(String RGB);
	
}
