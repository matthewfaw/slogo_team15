package front_end.view_modules.turtlestate;

import java.util.ArrayList;
import java.util.List;

import back_end.model.robot.IViewableRobot;
import front_end.sender.IRobotSender;
import front_end.view_modules.image_color_module.interfaces.IImageColorModule;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;

public class ConcreteAllRobotsStateBox implements IAllRobotsStateBox{

	private ScrollPane myScroller;
	private VBox myModule;
	private VBox myStateBox;
	private List<IRobotStateBox> myStateBoxes;
	private ComboBox<String> mySwitchMenu;
	private List<MenuItem> mySwitchList;
	
	private IRobotSender myRoboSender;
	private IImageColorModule myImageColorMap;

	private static final String TAB_TEXT = "Turtle ID: ";
	
	public ConcreteAllRobotsStateBox(IImageColorModule aImageColorMap){
		myImageColorMap = aImageColorMap;
		
		myModule = new VBox(0);		
		myStateBox = new VBox(0);
		myScroller = new ScrollPane();
		myStateBoxes = new ArrayList<>();
		
		mySwitchMenu = new ComboBox<>();
		mySwitchList = new ArrayList<>();
		myModule.getChildren().addAll(mySwitchMenu, myStateBox);
		
		myScroller.setHbarPolicy( ScrollBarPolicy.NEVER );
		myScroller.setVbarPolicy( ScrollBarPolicy.NEVER );
		
		myScroller.setContent(myModule);
		
		buildMenu();
	}
	
	public ConcreteAllRobotsStateBox( int aWidth, int aHeight, IImageColorModule aImageColorMap){
		this(aImageColorMap);
		myScroller.setMinSize(aWidth, aHeight);
		myScroller.setMaxSize(aWidth, aHeight);
	}
	
	private void buildMenu(){
		mySwitchList.clear();
		mySwitchMenu.getItems().clear();
		
		javafx.collections.ObservableList<String> tabs = FXCollections.observableArrayList();
		
		for(Integer i = 0; i < myStateBoxes.size(); i++ ){
			tabs.add(TAB_TEXT + myStateBoxes.get(i).getRobotID());
		}
		
		mySwitchMenu.setItems(tabs);
		mySwitchMenu.getSelectionModel()
					.selectedIndexProperty()
					.addListener( cl -> switchStateBox(mySwitchMenu.getSelectionModel().getSelectedIndex()) );
		mySwitchMenu.getSelectionModel().select(0);
		
	}
	
	private void switchStateBox(int aIndex){
		myStateBox.getChildren().clear();
		if (aIndex < 0) return;
		myStateBox.getChildren().add(myStateBoxes.get(aIndex).getInstanceAsNode());
	}
	
	@Override
	public void reset() {
		//buildMenu();
	}

	@Override
	public Node getInstanceAsNode() {
		return myScroller;
	}

	@Override
	public void giveRobot(IViewableRobot aViewRobot) {
		IRobotStateBox stateBox = new ConcreteRobotStateBox( myImageColorMap, aViewRobot , myRoboSender);
		myStateBoxes.add(stateBox);
		buildMenu();
	}

	@Override
	public void switchRobotTabs(int aRobotID) {
		if(aRobotID >= myStateBoxes.size()) return;
		switchStateBox(aRobotID);		
	}

	@Override
	public void giveRobotSender(IRobotSender aRoboSender) {
		myRoboSender = aRoboSender;
	}
	

}
