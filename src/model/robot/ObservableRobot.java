package model.robot;

public interface ObservableRobot {
	
	public void registerObserver(Observer o);
	
	public void removeObserver(Observer o);
	
	public void notifyObservers();

}
