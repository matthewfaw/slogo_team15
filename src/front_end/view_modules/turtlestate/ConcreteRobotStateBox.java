package front_end.view_modules.turtlestate;

import java.util.ResourceBundle;

import back_end.model.robot.IViewableRobot;
import front_end.sender.IRobotSender;
import front_end.view_modules.image_color_module.interfaces.IImageColorModule;
import front_end.view_modules.penProperties.PenPopup;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ConcreteRobotStateBox implements IRobotStateBox {

	private IImageColorModule myImageColorMap;
	private IViewableRobot myRobot;
	private IRobotSender myRoboSender;

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

	private static final int CHARACTER_SIZE = 20;
	
    private ResourceBundle myGUIResources;

    private Button myPenFunctions;
    private PenPopup myPenPopup;
	
	ConcreteRobotStateBox( IImageColorModule aImageColorMap, IViewableRobot aViewRobot, IRobotSender aRoboSender ){
		myImageColorMap = aImageColorMap;
		myRobot = aViewRobot;
		myRobot.registerObserver(this);
		myRoboSender = aRoboSender;
		
		myPenPopup = new PenPopup( aImageColorMap );
        myBox = new VBox(0);
        
        String initFile = "resources.frontend";
        String fileName = "/EnglishToolbar";
        myGUIResources = ResourceBundle.getBundle(initFile + fileName);
		
		myBox = new VBox(0);

		myIDLabel = new Label("n/a");
		myCoordinatesLabel = new Label("n/a");
		myBearingLabel = new Label("n/a");
		myRobotImage = new ImageView();
		myRobotImage.setFitHeight(CHARACTER_SIZE);
		myRobotImage.setFitWidth(CHARACTER_SIZE);
		
		myPenFunctions = new Button(myGUIResources.getString("PenButton"));
		onPenPress();
		
		initializePenToggle();
		initializeVisibilityToggle();
				
		myBox.getChildren().addAll(	myIDLabel,
			   	myCoordinatesLabel,
			   	myBearingLabel,
			   	myRobotImage,
			   	new HBox(myPenDownButton, myPenUpButton),
			   	new HBox(myVisibilityButton, myInvisibilityButton), 
			   	myPenFunctions);
	
		build();
	}

	private void initializePenToggle(){
		myPenDownButton = new RadioButton("Pen Down");
		myPenUpButton = new RadioButton("Pen Up");
		myPenGroup = new ToggleGroup();
		myPenDownButton.setToggleGroup(myPenGroup);
		myPenUpButton.setToggleGroup(myPenGroup);
		myPenGroup.selectToggle(myPenUpButton);
		
		myPenGroup.selectedToggleProperty().addListener( cl -> {
			if(myPenGroup.getSelectedToggle().equals(myPenUpButton)) myRobot.getPenInformation().setPenUp(true);
			if(myPenGroup.getSelectedToggle().equals(myPenDownButton)) myRobot.getPenInformation().setPenUp(false);
		});
	}
	
	private void initializeVisibilityToggle(){
		myVisibilityButton = new RadioButton("Visible");
		myInvisibilityButton = new RadioButton("Invisible");
		myVisibilityGroup = new ToggleGroup();
		myVisibilityButton.setToggleGroup(myVisibilityGroup);
		myInvisibilityButton.setToggleGroup(myVisibilityGroup);

		myVisibilityGroup.selectedToggleProperty().addListener( cl -> {
			if(myVisibilityGroup.getSelectedToggle().equals(myInvisibilityButton)) 
				myRoboSender.setVisibility(myRobot.getTurtleID(), false);
			if(myVisibilityGroup.getSelectedToggle().equals(myVisibilityButton)) 
				myRoboSender.setVisibility(myRobot.getTurtleID(), true);
		});
		
	}
	
	private void build(){
		myIDLabel.setText( "ID: " + Integer.toString( myRobot.getTurtleID() ) ); 
		myCoordinatesLabel.setText( buildCoordinateString(myRobot.getCurrentCoordinate().getX(), myRobot.getCurrentCoordinate().getY()) );
		myBearingLabel.setText("Angle: " + myRobot.getCurrentRotation() + " deg");
		
		if(myRobot.getPenInformation().isPenUp())
			myPenGroup.selectToggle(myPenUpButton);
		else 
			myPenGroup.selectToggle(myPenDownButton);
		
		if(myRobot.isVisible())
			myVisibilityGroup.selectToggle(myVisibilityButton);
		else 
			myVisibilityGroup.selectToggle(myInvisibilityButton);
		
		myRobotImage.setImage( new Image( myImageColorMap.getFile(0).toURI().toString() ) );
	}

	private String buildCoordinateString(Number aX, Number aY){
		return "Coord: (" + aX + ", " + aY + ")";
	}

	@Override
	public int getRobotID() {
		return myRobot.getTurtleID();
	}

    @Override
    public void reset () {
        // TODO Auto-generated method stub

    }

    @Override
    public Node getInstanceAsNode () {
        // TODO Auto-generated method stub
        return myBox;
    }

    @Override
    public void update () {
        build();
    }

    public void onPenPress () {
        myPenFunctions.setOnMouseClicked(e -> popupPenSelector());
    }
    
    private void popupPenSelector () {
        Stage stage = new Stage();
        Scene myScene = myPenPopup.getScene();
        stage.setScene(myScene);
        stage.show();
        configurePenPopup(stage);
    }
    
    private void configurePenPopup (Stage stage) {
        myPenPopup.onApplyPress(e -> collectPenInfo(stage));
        myPenPopup.onClearPress(e -> clearPenSettings());
    }
    
    private void clearPenSettings () {
        myPenPopup.clear();
    }

    private void collectPenInfo (Stage stage) {
        stage.hide();
        myPenPopup.getPenThickness();
    }

}
