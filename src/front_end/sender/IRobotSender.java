package front_end.sender;

import integration.drawing.PenInformation;

public interface IRobotSender {

	public void setVisibility(int aID, boolean isVisible);
	
	public void setPenInformation(int aID, PenInformation aPenInformation);
	
}
