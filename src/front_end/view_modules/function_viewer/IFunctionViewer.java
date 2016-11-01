package front_end.view_modules.function_viewer;

import java.util.List;
import front_end.acceptor.IFunctionAcceptor;
import front_end.view_modules.IViewModule;
import integration.languages.ILanguageSwitcher;
import javafx.scene.control.Button;


/**
 * @author George Bernard
 *
 *         Contains a ScriptStructure that listens for changes in the mediator,
 *         and updates if any changes have been made.
 *
 */
public interface IFunctionViewer extends IViewModule, ILanguageSwitcher, IFunctionAcceptor {

	
}
