package front_end.view_modules.history;

import java.util.ArrayList;
import java.util.List;
import back_end.model.states.methodhistory.IViewableUserInputHistory;
import integration.observe.IObserver;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * This class creates the concrete implementation of the history module. It implements the
 * history module interface and sets the specifics for how the history module will be laid out.
 * 
 * @author Kayla Schulz
 *
 */
class ConcreteHistoryModule implements IHistoryModule, IObserver {
    private ScrollPane myScroller;
    private HBox myToolbar;
    private VBox myModule;
    private VBox myHistoryColumn;
    private IViewableUserInputHistory myHistory;

    private static final String TITLE = "History Module";

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
    }

    public ConcreteHistoryModule (int aWidth, int aHeight) {
        this();
        myScroller.setPrefSize(aWidth, aHeight);
    }

    private HBox createHBox (String myString) {
        HBox myHBox = new HBox(SPACING);
        Label myLabel = new Label(myString.trim());
        myHBox.getChildren().addAll(myLabel);
        return myHBox;
    }

    public List<String> getCurFunction () {
        return myCurFunction;
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
    public void update () {
        myHistoryColumn.getChildren().clear();
        myHistory.getHistoryOfUserInputStrings()
                .forEach(s -> myHistoryColumn.getChildren().add(createHBox(s)));
    }

}
