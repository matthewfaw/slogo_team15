package front_end.appScene;

public enum DefaultStates {
                           MY_PEN_COLOR("black"),
                           TURTLE_IMAGE("src/resources/images/turtle.png");

    private String myDefaults;

    DefaultStates (String s) {
        this.myDefaults = s;
    }

}
