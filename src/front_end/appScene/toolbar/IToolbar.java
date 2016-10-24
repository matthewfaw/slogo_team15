package front_end.appScene.toolbar;

import java.util.Map;
import front_end.appScene.IViewer;
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
public interface IToolbar extends IViewer {

    public void onBuildPress (EventHandler<MouseEvent> aEvent);

    public void onRunPress (EventHandler<MouseEvent> aEvent);

    public void onStepPress (EventHandler<MouseEvent> aEvent);

    public void onHelpPress (EventHandler<MouseEvent> aEvent);

    public void onLanguageSelect (Map<Languages, EventHandler<ActionEvent>> aLanguageEvent);

    public void onResetPress (EventHandler<MouseEvent> aEvent);
}
