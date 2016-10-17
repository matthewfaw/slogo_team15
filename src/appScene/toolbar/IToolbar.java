package appScene.toolbar;

import appScene.IViewer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * @author George Bernard
 *
 * Mediator listens to many (but not all) of these buttons.
 *
 */
public interface IToolbar extends IViewer {

	public void onCompilePress(EventHandler<MouseEvent> event);
	
	public void onRunPress(EventHandler<MouseEvent> event);
	
	public void onStepPress(EventHandler<MouseEvent> event);
	
	public void onHelpPress(EventHandler<MouseEvent> event);
	
	public void onLanguagePress(EventHandler<MouseEvent> event);
	
	public void onClearPress(EventHandler<MouseEvent> event);
}
