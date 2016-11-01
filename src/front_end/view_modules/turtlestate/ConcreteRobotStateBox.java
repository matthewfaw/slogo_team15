package front_end.view_modules.turtlestate;

import back_end.model.robot.IViewableRobot;
import front_end.view_modules.image_color_module.interfaces.IColorModule;
import front_end.view_modules.image_color_module.interfaces.IImageModule;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConcreteRobotStateBox implements IRobotStateBox {

	private IColorModule myColorMap;
	private IImageModule myImageMap;
	private IViewableRobot myRobot;

	private VBox myBox;

	private Label myIDLabel;
	private Label myCoordinatesLabel;
	private Label myBearingLabel;
	
	private ToggleGroup myPenGroup;
	private ToggleButton myPenDownButton;
	private ToggleButton myPenUpButton;
	
	private ToggleGroup  myVisibilityGroup;
	private ToggleButton myVisibilityButton;
	private ToggleButton myInvisibilityButton;
	private ImageView myRobotImage;

	private boolean myIsBuilt;

	private static final int CHARACTER_SIZE = 20;
	
	ConcreteRobotStateBox( IColorModule aColorMap, IImageModule aImageMap ){
		myColorMap = aColorMap;
		myImageMap = aImageMap;
		myBox = new VBox(0);
		myIsBuilt = false;

		myIDLabel = new Label("n/a");
		myCoordinatesLabel = new Label("n/a");
		myBearingLabel = new Label("n/a");
		myRobotImage = new ImageView();
		myRobotImage.setFitHeight(CHARACTER_SIZE);
		myRobotImage.setFitWidth(CHARACTER_SIZE);
		
		myPenDownButton = new RadioButton("Pen Down");
		myPenUpButton = new RadioButton("Pen Up");
		myPenGroup = new ToggleGroup();
		myPenGroup.selectedToggleProperty().addListener( t -> {});
		myPenDownButton.setToggleGroup(myPenGroup);
		myPenUpButton.setToggleGroup(myPenGroup);
		
		myVisibilityButton = new RadioButton("Visible");
		myInvisibilityButton = new RadioButton("Invisible");
		myVisibilityGroup = new ToggleGroup();
		myVisibilityGroup.selectedToggleProperty().addListener( t -> {});
		myVisibilityButton.setToggleGroup(myVisibilityGroup);
		myInvisibilityButton.setToggleGroup(myVisibilityGroup);

		
		myBox.getChildren().addAll(	myIDLabel,
			   	myCoordinatesLabel,
			   	myBearingLabel,
			   	myRobotImage,
			   	new HBox(myPenDownButton, myPenUpButton),
			   	new HBox(myVisibilityButton, myInvisibilityButton));
	
		build();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public Node getInstanceAsNode() {
		// TODO Auto-generated method stub
		return myBox;
	}

	@Override
	public void update() {
		if(noRobot() || !myIsBuilt) return;
		build();
	}

	@Override
	public void giveRobot(IViewableRobot aViewRobot) {
		myRobot = aViewRobot;
		myRobot.registerObserver(this);
		build();
	}

	private void build(){
		if(noRobot()) return;
		myIDLabel.setText( "ID: " + Integer.toString( myRobot.getTurtleID() ) ); 
		myCoordinatesLabel.setText( buildCoordinateString(myRobot.getCoordinate().getX(), myRobot.getCoordinate().getY()) );
		myBearingLabel.setText("Angle: " + myRobot.getRotation() + " deg");
		
		if(myRobot.getPenInformation().isPenUp())
			myPenGroup.selectToggle(myPenUpButton);
		else 
			myPenGroup.selectToggle(myPenDownButton);
		
		if(myRobot.isVisible())
			myVisibilityGroup.selectToggle(myVisibilityButton);
		else 
			myVisibilityGroup.selectToggle(myInvisibilityButton);
		
		myRobotImage.setImage( new Image( myImageMap.getFile(0).toURI().toString() ) );
		myIsBuilt = true;
	}

	private String buildCoordinateString(Number aX, Number aY){
		return "Coord: (" + aX + ", " + aY + ")";
	}

	private boolean noRobot() {
		return (myRobot == null);
	}

	@Override
	public int getRobotID() {
		// TODO Auto-generated method stub
		return myRobot.getTurtleID();
	}


}
