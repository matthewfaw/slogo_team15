package front_end.view_modules.history;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

class ConcreteHistoryModule implements IHistoryModule {

	private ScrollPane myScroller;
	private HBox myToolbar;
	private VBox myModule;
	private VBox myHistoryColumn;
	
	private static final String TITLE = "History Module";
	
	ConcreteHistoryModule(){
		myScroller = new ScrollPane();
		myScroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		myScroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
		myModule = new VBox(5);
		myHistoryColumn = new VBox(5);
		myToolbar = buildToolbar();
		
		myModule.getChildren().addAll(myToolbar, myHistoryColumn);
		myScroller.setContent(myModule);
		
		// Test Code - Visualize without backend sending stuff
		// ArrayList<String> test = new ArrayList<String>(Arrays.asList(new String[]{"History", "So much History", "NewLine \n Test \n so \n love me senpai"}));
		// test.forEach( s -> giveHistory(s));
		
	}
	
	public ConcreteHistoryModule(int aWidth, int aHeight) {
		this();
		myScroller.setPrefSize(aWidth, aHeight);
	}

	@Override
	public void reset() {
		myHistoryColumn.getChildren().clear();
	}

	@Override
	public Node getInstanceAsNode() {
		return myScroller;
	}

	@Override
	public void giveHistory(String aHistory) {
		myHistoryColumn.getChildren().add( new HistoryRow(aHistory).getNode() );
	}

	private HBox buildToolbar(){
		HBox toolbar = new HBox();
		Label title = new Label(TITLE);
		toolbar.getChildren().add(title);
		return toolbar;
	}
	
}
