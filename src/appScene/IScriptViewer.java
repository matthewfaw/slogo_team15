package appScene;

/**
 * @author George Bernard
 *
 * Contains a ScriptStructure that listens for changes in the mediator,
 * and updates if any changes have been made.
 *
 */
public interface IScriptViewer {
	
	 /**
     * Clear the text editor to its original, empty state
     */
    public void clear();
    
    public void giveScriptStructure();
    
    public void onScriptPress();
}
