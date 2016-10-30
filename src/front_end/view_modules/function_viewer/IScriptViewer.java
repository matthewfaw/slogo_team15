package front_end.view_modules.function_viewer;

import front_end.view_modules.IViewModule;
import integration.languages.ILanguageSwitcher;


/**
 * @author George Bernard
 *
 *         Contains a ScriptStructure that listens for changes in the mediator,
 *         and updates if any changes have been made.
 *
 */
public interface IScriptViewer extends IViewModule, ILanguageSwitcher {

    public void giveScriptStructure ();

    public void onScriptPress ();
}
