package back_end.model.node;

import back_end.model.exception.InvalidNodeUsageException;

public interface IReadableInput {

    public String getName () throws InvalidNodeUsageException;

    public double getValue () throws InvalidNodeUsageException;

}
