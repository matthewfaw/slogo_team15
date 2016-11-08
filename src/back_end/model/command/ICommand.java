package back_end.model.command;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;

/**
 * This is the interface class for all Commands - it allows for them to have all the same constructor and it guarantees
 * that they are going to return a double as the return value
 *
 */

public interface ICommand {

    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException;

}
