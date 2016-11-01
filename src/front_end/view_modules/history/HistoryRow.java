package front_end.view_modules.history;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

class HistoryRow {

	private HBox myRow;
	private Label myInfo;
	
	private HistoryRow(){
		myRow = new HBox(0);
		myRow.applyCss();
	}
	
	HistoryRow(String aHistory){
		this();
		myInfo = new Label(aHistory);
		myInfo.setWrapText(true);
		myRow.getChildren().add(myInfo);
	}

	public Node getNode() {
		return myRow;
	}

}
