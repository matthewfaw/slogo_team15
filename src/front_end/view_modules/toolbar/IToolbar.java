package front_end.view_modules.toolbar;

import java.util.Map;

import front_end.view_modules.ILanguageSwitcher;
import front_end.view_modules.IViewModule;
import integration.languages.Languages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


/**
 * @author George Bernard
 *
 *         Mediator listens to many (but not all) of these buttons.
 *
 */
public interface IToolbar extends IViewModule, ILanguageSwitcher {

    public void onBuildPress (EventHandler<MouseEvent> aEvent);

    public void onRunPress (EventHandler<MouseEvent> aEvent);

    public void onStepPress (EventHandler<MouseEvent> aEvent);

    public void onHelpPress (EventHandler<MouseEvent> aEvent);

    public void onLanguageSelect (Map<Languages, EventHandler<ActionEvent>> aLanguageEvent);

    public void onResetPress (EventHandler<MouseEvent> aEvent);
    
    public void onPenPress (EventHandler<MouseEvent> aEvent);
}
