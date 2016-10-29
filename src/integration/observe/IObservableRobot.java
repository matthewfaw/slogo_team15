package integration.observe;

public interface IObservableRobot {

    public void registerObserver (IRobotObserver o);

    public void removeObserver (IRobotObserver o);

    public void notifyObservers ();

}
