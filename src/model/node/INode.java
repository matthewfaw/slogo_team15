package model.node;

import model.exception.ArgumentException;

public interface INode {
	
	public double eval(IReadableInput...aList) throws ArgumentException;

}
