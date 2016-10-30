package integration.observe;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
	
	private List<IObserver> myObservers;
	
	public Observable() {
		myObservers = new ArrayList<IObserver>();
	}

	public void registerObserver(IObserver o) {
		myObservers.add(o);
	}


	public void removeObserver(IObserver o) {
		int i = myObservers.indexOf(o);
		if (i > 0) {
			myObservers.remove(i);
		}
	}

	public void notifyObservers() {
		for (IObserver observer : myObservers) {
			observer.update();
		}
	}

}
