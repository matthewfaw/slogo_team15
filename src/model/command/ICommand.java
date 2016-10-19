package model.command;

import model.exception.ArgumentException;

public interface ICommand {
	

	public void eval(String...aList) throws ArgumentException;
	
}
