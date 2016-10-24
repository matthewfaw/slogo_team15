package integration.observe;

public interface IObservable {
	
	public void registerObserver(IRobotObserver o);
	
	public void removeObserver(IRobotObserver o);
	
	public void notifyObservers();

}
