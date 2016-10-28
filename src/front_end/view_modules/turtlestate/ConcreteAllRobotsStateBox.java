package front_end.view_modules.turtlestate;

import java.util.ArrayList;
import java.util.List;

import back_end.model.robot.IViewRobot;
import front_end.view_modules.shape_color_module.interfaces.IColorModule;
import front_end.view_modules.shape_color_module.interfaces.IShapeModule;
import integration.languages.Languages;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;

public class ConcreteAllRobotsStateBox implements IAllRobotsStateBox{

	private ScrollPane myScroller;
	private VBox myBox;
	private List<IRobotStateBox> myStateBoxes;
	
	private IColorModule myColorMap;
	private IShapeModule myImageMap;
	
	public ConcreteAllRobotsStateBox( IColorModule aColorMap, IShapeModule aImageMap){
		myColorMap = aColorMap;
		myImageMap = aImageMap;
		
		myBox = new VBox(0);		
		myScroller = new ScrollPane();
		myStateBoxes = new ArrayList<>();
			
		myScroller.setHbarPolicy( ScrollBarPolicy.NEVER );
		myScroller.setVbarPolicy( ScrollBarPolicy.ALWAYS );
		
		myScroller.setContent(myBox);
		
		IRobotStateBox stateBox = new ConcreteRobotStateBox( myColorMap, myImageMap );
		myStateBoxes.add(stateBox);
		build();
	}
	
	private void build(){
		myBox.getChildren().clear();
		myStateBoxes.forEach( stateBox -> { myBox.getChildren().add(stateBox.getInstanceAsNode());});
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
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
		build();
	}
	
	

}
