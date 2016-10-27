package front_end.appScene.scriptViewer;

import front_end.ILanguageSwitcher;
import front_end.appScene.IViewModule;


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
