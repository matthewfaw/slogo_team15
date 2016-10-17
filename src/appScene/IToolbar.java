package appScene;

/**
 * @author George Bernard
 *
 * Mediator listens to many (but not all) of these buttons.
 *
 */
public interface IToolbar {

	public void onCompilePress();
	
	public void onRunPress();
	
	public void onStepPress();
	
	public void onHelpPress();
	
	public void onLanguagePress();
	
	public void onClearPress();
}
