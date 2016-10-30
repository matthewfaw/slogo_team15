package front_end.view_modules.helpPage;

import front_end.view_modules.IViewModule;
import integration.languages.ILanguageSwitcher;

/**
 * 
 * @author Kayla Schulz
 *
 */
public interface IHelpPage extends IViewModule, ILanguageSwitcher {

    public void loadHelpPage ();

}
