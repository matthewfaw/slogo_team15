package back_end.model.exception;

public class EmptyInputException extends Exception {

    private static final long serialVersionUID = 1L;

    public EmptyInputException () {
    }

    public EmptyInputException (String aMessage) {
        super(aMessage);
    }


}
