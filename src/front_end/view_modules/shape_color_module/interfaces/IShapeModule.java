package front_end.view_modules.shape_color_module.interfaces;

import front_end.view_modules.ILanguageSwitcher;
import front_end.view_modules.IViewModule;

public interface IShapeModule extends IViewModule, ILanguageSwitcher {

	/**
	 *  Returns filename of current image mapped to by integer aImageId
	 * 
	 *  If aImageId is not a valid key, it returns the default image
	 * 
	 * @param aImageId
	 * @return
	 */
	public String getFilename(int aImageId);
	
}
