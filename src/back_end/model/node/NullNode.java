package back_end.model.node;

import java.util.List;
import back_end.model.exception.ArgumentException;


public class NullNode extends Node {

    public NullNode () {
        super();
    }

    @Override
    public String getName () {
        return null;
    }

    @Override
    public double getValue () {
        return 0;
    }

    @Override
    public double eval () throws ArgumentException {
        // TODO Auto-generated method stub
        return 0;
    }
}
