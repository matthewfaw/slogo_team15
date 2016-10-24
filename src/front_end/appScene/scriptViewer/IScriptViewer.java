package front_end.appScene.scriptViewer;

import front_end.appScene.IViewer;


/**
 * @author George Bernard
 *
 *         Contains a ScriptStructure that listens for changes in the mediator,
 *         and updates if any changes have been made.
 *
 */
public interface IScriptViewer extends IViewer {

    public void giveScriptStructure ();

    public void onScriptPress ();
}
