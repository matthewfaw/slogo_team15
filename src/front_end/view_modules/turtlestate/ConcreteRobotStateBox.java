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

	ConcreteRobotStateBox( IColorModule aColorMap, IShapeModule aImageMap ){
		myColorMap = aColorMap;
		myImageMap = aImageMap;
		myBox = new VBox(0);
		myIsBuilt = false;

		myIDLabel = new Label("n/a");
		myCoordinatesLabel = new Label("n/a");
		myBearingLabel = new Label("n/a");

		myPenDownButton = new ToggleButton("Pen Up");

		myPenDownButton.getToggleGroup()
		.selectedToggleProperty() 
		// Set Change Text if toggled
		.addListener( (ObservableValue<? extends Toggle> ov,Toggle toggle, Toggle new_toggle) -> {
			if(new_toggle == null) return;
			else{
				if(new_toggle.isSelected()){ myPenDownButton.setText("Pen Down");}
				else{ myPenDownButton.setText("Pen Up"); 
				}
			}
		});

		myVisibilityButton = new ToggleButton("Visible");
		myVisibilityButton.getToggleGroup()
		.selectedToggleProperty()
		// Set Change Text if toggled
		.addListener( (ObservableValue<? extends Toggle> ov,Toggle toggle, Toggle new_toggle) -> {
			if(new_toggle == null) return;
			else{
				if(new_toggle.isSelected()){ myPenDownButton.setText("Invisible");}
				else{ myPenDownButton.setText("Visible"); 
				}
			}
		});
		
		myBox.getChildren().addAll(	myIDLabel,
			   	myCoordinatesLabel,
			   	myBearingLabel,
			   	myPenDownButton,
			   	myVisibilityButton);
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
	}

	@Override
	public void giveRobot(IViewRobot aViewRobot) {
		myRobot = aViewRobot;
		build();
	}



	private void build(){
		if(noRobot()) return;
		myIDLabel.setText(myRobot.toString()); //TODO change this to robotID
		myCoordinatesLabel.setText( buildCoordinateString(myRobot.getX(), myRobot.getY()) );
		myBearingLabel.setText("Angle: " + myRobot.getRotation() + " deg");
		myPenDownButton.setSelected(myRobot.isPenDown());
		myVisibilityButton.setSelected(myRobot.isVisible());
		myIsBuilt = true;
	}

	private String buildCoordinateString(Number aX, Number aY){
		return "Coord: (" + aX + ", " + aY + ")";
	}

	private boolean noRobot() {
		return (myRobot == null);
	}


}
