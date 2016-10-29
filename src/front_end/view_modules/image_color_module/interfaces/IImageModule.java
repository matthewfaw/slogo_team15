package front_end.view_modules.image_color_module.interfaces;

import java.io.File;

import front_end.view_modules.ILanguageSwitcher;
import front_end.view_modules.IViewModule;

public interface IImageModule extends IViewModule, ILanguageSwitcher {

	/**
	 *  Returns filename of current image mapped to by integer aImageId
	 * 
	 *  If aImageId is not a valid key, it returns the default image
	 * 
	 * @param aImageId
	 * @return
	 */
	public File getFile(int aImageId);
	
}
