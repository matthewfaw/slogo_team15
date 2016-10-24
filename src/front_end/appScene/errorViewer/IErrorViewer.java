package front_end.appScene.errorViewer;

import front_end.appScene.IViewer;

/**
 * @author George Bernard
 *
 * Contains ErrorStructure that listens for changes seen by the Mediator
 * And then updates any line by line or syntax errors.
 *
 */
public interface IErrorViewer extends IViewer {
    
    public void giveErrorStructure(Exception aError);
    
    public void onErrorPress();
	
}
