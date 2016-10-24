package model.robot;

import integration.IRobotObserver;

public interface Observable {
	
	public void registerObserver(IRobotObserver o);
	
	public void removeObserver(IRobotObserver o);
	
	public void notifyObservers();

}
