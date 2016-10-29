package front_end.view_modules.turtlestate;

import back_end.model.robot.IViewRobot;
import front_end.view_modules.shape_color_module.interfaces.IColorModule;
import front_end.view_modules.shape_color_module.interfaces.IShapeModule;
import integration.languages.Languages;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;

public class ConcreteRobotStateBox implements IRobotStateBox {

	private IColorModule myColorMap;
	private IShapeModule myImageMap;
	private IViewRobot myRobot;

	private VBox myBox;

	private Label myIDLabel;
	private Label myCoordinatesLabel;
	private Label myBearingLabel;
	private ToggleButton myPenDownButton;
	private ToggleButton myVisibilityButton;
	private ImageView myRobotImage;

	private boolean myIsBuilt;

	private static final int CHARACTER_SIZE = 20;
	
	ConcreteRobotStateBox( IColorModule aColorMap, IShapeModule aImageMap ){
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
		
		myPenDownButton = new ToggleButton("Pen Up");

		ToggleGroup group1 = new ToggleGroup();
		group1.selectedToggleProperty() 
		// Set Change Text if toggled
		.addListener( (ObservableValue<? extends Toggle> ov,Toggle old_toggle, Toggle new_toggle) -> {
			myPenDownButton.setText("Pen Up");
			if(new_toggle == null) return;
			if(new_toggle.isSelected()) myPenDownButton.setText("Pen Down");
		});
		myPenDownButton.setToggleGroup(group1);
		
		myVisibilityButton = new ToggleButton("Visible");
		ToggleGroup group2 = new ToggleGroup();
		group2.selectedToggleProperty()
		// Set Change Text if toggled
		.addListener( (ObservableValue<? extends Toggle> ov,Toggle old_toggle, Toggle new_toggle) -> {
			myVisibilityButton.setText("Visible");
			if(new_toggle == null) return;
			if(new_toggle.isSelected()) myVisibilityButton.setText("Invisible");
			}
		);
		myVisibilityButton.setToggleGroup(group2);

		myBox.getChildren().addAll(	myIDLabel,
			   	myCoordinatesLabel,
			   	myBearingLabel,
			   	myRobotImage,
			   	myPenDownButton,
			   	myVisibilityButton);
	
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
	public void switchLanguage(Languages aLanguage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		if(noRobot() || !myIsBuilt) return;
		build();
	}

	@Override
	public void giveRobot(IViewRobot aViewRobot) {
		myRobot = aViewRobot;
		myRobot.registerObserver(this);
		build();
	}

	private void build(){
		// if(noRobot()) return;
		myIDLabel.setText("Turtle 1"); //myRobot.toString()); //TODO change this to robotID
		myCoordinatesLabel.setText( "x, y" ); //buildCoordinateString(myRobot.getX(), myRobot.getY()) );
		myBearingLabel.setText("Angle: 360 deg" ); //+ myRobot.getRotation() + " deg");
		myPenDownButton.setSelected( false ); // myRobot.isPenDown());
		myVisibilityButton.setSelected( true ); //myRobot.isVisible());
		myRobotImage.setImage( new Image( myImageMap.getFile(2).toURI().toString() ) );
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
		return 0;
	}


}