package front_end.sender;

public interface IColorSender {

	public void newColor(String aRGB);
	
 	public void setColor(int aColorID, String aRGB);
	
 	public void setBackground(int aColorID);
 	
 	public void setBackground(String RGB);
	
}
