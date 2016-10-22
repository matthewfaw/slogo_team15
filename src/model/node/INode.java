package model.node;

import model.exception.ArgumentException;

public interface INode {
	
	public double eval(String...aList) throws ArgumentException;

}
