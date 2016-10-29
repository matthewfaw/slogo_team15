package front_end.view_modules.turtlestate;

import java.util.ArrayList;
import java.util.List;

import back_end.model.robot.IViewRobot;
import front_end.view_modules.shape_color_module.interfaces.IColorModule;
import front_end.view_modules.shape_color_module.interfaces.IShapeModule;
import integration.languages.Languages;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;

public class ConcreteAllRobotsStateBox implements IAllRobotsStateBox{

	private ScrollPane myScroller;
	private VBox myModule;
	private VBox myStateBox;
	private List<IRobotStateBox> myStateBoxes;
	private MenuButton mySwitchMenu;
	private List<MenuItem> mySwitchList;
	
	private IColorModule myColorMap;
	private IShapeModule myImageMap;
	
	private static final String TAB_TEXT = "Turtle ID: ";
	
	public ConcreteAllRobotsStateBox( IColorModule aColorMap, IShapeModule aImageMap){
		myColorMap = aColorMap;
		myImageMap = aImageMap;
		
		myModule = new VBox(0);		
		myStateBox = new VBox(0);
		myScroller = new ScrollPane();
		myStateBoxes = new ArrayList<>();
		
		mySwitchMenu = new MenuButton(TAB_TEXT);
		mySwitchList = new ArrayList<>();
		myModule.getChildren().addAll(mySwitchMenu, myStateBox);
		
		myScroller.setHbarPolicy( ScrollBarPolicy.NEVER );
		myScroller.setVbarPolicy( ScrollBarPolicy.NEVER );
		
		myScroller.setContent(myModule);
		
		buildMenu();
	}
	
	private void buildMenu(){
		mySwitchList.clear();
		mySwitchMenu.getItems().clear();
		
		for(Integer i = 0; i < myStateBoxes.size(); i++ ){
			MenuItem tab = new MenuItem("Turtle " + Integer.toString(i));
			mySwitchMenu.getItems().add(tab);
			IRobotStateBox stateBox = myStateBoxes.get(i);
			tab.setOnAction( event -> switchStateBox(stateBox ));
		}
		
	}
	
	private void switchStateBox(IRobotStateBox aStateBox){
		myStateBox.getChildren().clear();
		mySwitchMenu.setText(TAB_TEXT + Integer.toString(aStateBox.getRobotID()));
		myStateBox.getChildren().add(aStateBox.getInstanceAsNode());
	}
	
	@Override
	public void reset() {
		buildMenu();
	}

	@Override
	public Node getInstanceAsNode() {
		// TODO Auto-generated method stub
		return myScroller;
	}

	@Override
	public void switchLanguage(Languages aLanguage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void giveRobot(IViewRobot aViewRobot) {
		IRobotStateBox stateBox = new ConcreteRobotStateBox( myColorMap, myImageMap );
		stateBox.giveRobot(aViewRobot);
		myStateBoxes.add(stateBox);
		buildMenu();
	}

	@Override
	public void update() {
		// Does Nothing, this class just passes Robots along
	}

	@Override
	public void switchRobotTabs(int aRobotID) {
		if(aRobotID >= myStateBoxes.size()) return;
		switchStateBox(myStateBoxes.get(aRobotID));		
	}
	
	

}
