package front_end.view_modules.history;

import integration.acceptors.IHistoryAcceptor;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

class ConcreteHistoryModule implements IHistoryModule, IHistoryAcceptor {

	private ScrollPane myScroller;
	
	
	ConcreteHistoryModule(){
		
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getInstanceAsNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
