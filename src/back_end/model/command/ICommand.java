package back_end.model.command;

import back_end.model.exception.InvalidNodeUsageException;
import back_end.model.node.IReadableInput;

/**
 * @author matthewfaw
 * @author Hannah Fuchshuber
 * 
 * This is the interface class for all Commands - it allows for them to have all the same constructor and it guarantees
 * that they are going to return a double as the return value
 *
 */

public interface ICommand {

	/**
	 * Method to perform the evaluation of the node specific to the command
	 * This method throws an exception if the method tries to access a node element that
	 * it shouldn't
	 * This method assumes that the proper number of inputs is given
	 * 
	 * @param aList: an arbitrarily sized list of ReadableInputs to be evaluated by the method
	 * @return the evaluation of the inputs
	 * @throws InvalidNodeUsageException
	 */
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException;

}
