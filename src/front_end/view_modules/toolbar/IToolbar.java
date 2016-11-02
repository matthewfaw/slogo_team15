package front_end.view_modules.toolbar;

import java.util.Map;

import front_end.view_modules.IViewModule;
import integration.languages.ILanguageSwitcher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


/**
 * @author George Bernard
 *
 *         Mediator listens to many (but not all) of these buttons.
 *
 */
public interface IToolbar extends IViewModule, ILanguageSwitcher {

	public enum ButtonTypes{
		BUILD,
		RUN,
		STEP_INSTRUCTION,
		STEP_LINE,
		HELP,
		LANGUAGE,
		RESET,
		PEN;
	}
	
	public Button getButton(ButtonTypes aButton);
	
    public void onBuildPress (EventHandler<MouseEvent> aEvent);

    public void onRunPress (EventHandler<MouseEvent> aEvent);

    public void onStepInstrPress (EventHandler<MouseEvent> aEvent);
    
    public void onStepLinePress (EventHandler<MouseEvent> aEvent);

    public void onHelpPress (EventHandler<MouseEvent> aEvent);

    public void onLanguageSelect (Map<Languages, EventHandler<ActionEvent>> aLanguageEvent);

    public void onResetPress (EventHandler<MouseEvent> aEvent);
   
    public void onPenPress (EventHandler<MouseEvent> aEvent);
}
