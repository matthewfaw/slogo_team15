package appScene;


/**
 * @author George Bernard
 *
 * Contains ErrorStructure that listens for changes seen by the Mediator
 * And then updates any line by line or syntax errors.
 *
 */
public interface IErrorViewer {

	 /**
     * Clear the text editor to its original, empty state
     */
    public void clear();
    
    public void giveErrorStructure(Object ErrorStructure);
    
    public void onErrorPress();
	
}
