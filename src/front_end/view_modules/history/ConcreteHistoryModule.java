package front_end.view_modules.history;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import back_end.model.states.methodhistory.IViewableUserInputHistory;
import integration.observe.IObserver;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


class ConcreteHistoryModule implements IHistoryModule, IObserver {
    private ScrollPane myScroller;
    private HBox myToolbar;
    private VBox myModule;
    private VBox myHistoryColumn;
    private IViewableUserInputHistory myHistory;

    private static final String TITLE = "History Module";
    private ResourceBundle myGUIResources;

    private final int SPACING = 10;
    private List<String> myCurFunction;

    ConcreteHistoryModule () {
        myScroller = new ScrollPane();
        myScroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        myScroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        myCurFunction = new ArrayList<String>();

        myModule = new VBox(5);
        myHistoryColumn = new VBox(5);
        myToolbar = buildToolbar();

        myModule.getChildren().addAll(myToolbar, myHistoryColumn);
        myScroller.setContent(myModule);

        String initFile = "resources.frontend";
        String fileName = "/EnglishButtonState";
        myGUIResources = ResourceBundle.getBundle(initFile + fileName);
    }

    public ConcreteHistoryModule (int aWidth, int aHeight) {
        this();
        myScroller.setPrefSize(aWidth, aHeight);
    }

    private HBox createHBox (String myString) {
        HBox myHBox = new HBox(SPACING);
        Label myLabel = new Label(myString.trim());
        Button myButton = makeButton(myString);
        myButton.setOnMouseClicked(e -> load(myString));
        myHBox.getChildren().addAll(myLabel, myButton);
        return myHBox;
    }

    private void load (String myString) {
        myCurFunction = Arrays.asList(myString.split("\n"));
    }

    public List<String> getCurFunction () {
        return myCurFunction;
    }

    private Button makeButton (String myFunctionString) {
        Button myButton = new Button(myGUIResources.getString("HistoryButton"));
        return myButton;
    }

    @Override
    public void reset () {
        myHistoryColumn.getChildren().clear();
    }

    @Override
    public Node getInstanceAsNode () {
        return myScroller;
    }

    @Override
    public void giveHistory (IViewableUserInputHistory aHistory) {
    	myHistory = aHistory;
    	myHistory.registerObserver(this);
    }

    private HBox buildToolbar () {
        HBox toolbar = new HBox();
        Label title = new Label(TITLE);
        toolbar.getChildren().add(title);
        return toolbar;
    }

	@Override
	public void update() {
		myHistoryColumn.getChildren().clear();
		myHistory.getHistoryOfUserInputStrings().forEach( s -> myHistoryColumn.getChildren().add(createHBox(s)));
	}

}
