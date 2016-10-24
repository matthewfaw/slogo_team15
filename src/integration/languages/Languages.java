package integration.languages;

public enum Languages {
                       DEFAULT("English"),
                       CHINESE("Chinese"),
                       FRENCH("French"),
                       GERMAN("German"),
                       ITALIAN("Italian"),
                       PORTUGESE("Portugese"),
                       RUSSIAN("Russian"),
                       SPANISH("Spanish");

    private static final String INIT_FILE = "resources.languages.";
    private final String myName;

    private Languages (String aFilename) {
        myName = aFilename;
    }

    public String getName () {
        return myName;
    }

    public String getFileLocation () {
        return INIT_FILE + myName;
    }

}
