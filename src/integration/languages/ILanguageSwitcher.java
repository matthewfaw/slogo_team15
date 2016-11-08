package integration.languages;

/**
 * The ILanguageSwitcher is meant for any object must 
 * switch language using the attached language enum
 * 
 * @author George Bernard	
 */
public interface ILanguageSwitcher {

	/**
	 * Enumerates the languages that ILanguageSwitcher can select between
	 *  
	 * @author George Bernard
	 */
	public enum Languages {
		DEFAULT("English"),
		CHINESE("Chinese"),
		FRENCH("French"),
		GERMAN("German"),
		ITALIAN("Italian"),
		PORTUGESE("Portugese"),
		RUSSIAN("Russian"),
		SPANISH("Spanish");

		// File location of language resource files
		private static final String INIT_FILE = "resources.languages.";
		private final String myName;

		private Languages (String aFilename) {
			myName = aFilename;
		}

		/**
		 * Returns the name of the language
		 * 
		 * @return Language Name
		 */
		public String getName () {
			return myName;
		}

		/**
		 * Returns the file location of the resource file 
		 * relating to this language
		 * 
		 * @return file location of language resource file
		 */
		public String getFileLocation () {
			return INIT_FILE + myName;
		}

	}

	/**
	 * Method switches the language of the object
	 * 
	 * @param aLanguage
	 */
	public void switchLanguage (Languages aLanguage);

}
