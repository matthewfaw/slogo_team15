package model.command;

import model.exception.ArgumentException;

public interface ICommand {
	

	public double eval(String...aList) throws ArgumentException;
	
}
