package front_end.view_modules.scriptViewer;

import front_end.view_modules.ILanguageSwitcher;
import front_end.view_modules.IViewModule;


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
