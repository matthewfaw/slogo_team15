package front_end.view_modules.turtlestate;

import java.util.ResourceBundle;
import back_end.model.robot.IViewableRobot;
import front_end.view_modules.image_color_module.interfaces.IImageColorModule;
import front_end.view_modules.image_color_module.interfaces.IImageModule;
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

    private IImageColorModule myImageMap;
    private IViewableRobot myRobot;
    
    private ResourceBundle myGUIResources;

    private VBox myBox;

    private Label myIDLabel;
    private Label myCoordinatesLabel;
    private Label myBearingLabel;

    private ToggleGroup myVisibilityGroup;
    private ToggleButton myVisibilityButton;
    private ToggleButton myInvisibilityButton;
    private ImageView myRobotImage;
    
    private Button myPenFunctions;
    private PenPopup myPenPopup;

    private static final int CHARACTER_SIZE = 20;

    ConcreteRobotStateBox (IImageColorModule aImageMap, IViewableRobot aViewRobot) {
        myImageMap = aImageMap;
        myRobot = aViewRobot;
        myRobot.registerObserver(this);
        
        myPenPopup = new PenPopup();

        myBox = new VBox(0);
        
        String initFile = "resources.frontend";
        String fileName = "/EnglishToolbar";
        myGUIResources = ResourceBundle.getBundle(initFile + fileName);

        myIDLabel = new Label("n/a");
        myCoordinatesLabel = new Label("n/a");
        myBearingLabel = new Label("n/a");
        myRobotImage = new ImageView();
        myRobotImage.setFitHeight(CHARACTER_SIZE);
        myRobotImage.setFitWidth(CHARACTER_SIZE);
        
        myPenFunctions = new Button(myGUIResources.getString("PenButton"));

        myVisibilityButton = new RadioButton("Visible");
        myInvisibilityButton = new RadioButton("Invisible");
        myVisibilityGroup = new ToggleGroup();
        myVisibilityGroup.selectedToggleProperty().addListener(t -> {
        });
        myVisibilityButton.setToggleGroup(myVisibilityGroup);
        myInvisibilityButton.setToggleGroup(myVisibilityGroup);

        myBox.getChildren().addAll(myIDLabel,
                                   myCoordinatesLabel,
                                   myBearingLabel,
                                   myRobotImage,
                                   myPenFunctions,
                                   new HBox(myVisibilityButton, myInvisibilityButton));

        onPenPress( e -> popupPenSelector() );
        
        build();
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

    private void build () {
        myIDLabel.setText("ID: " + Integer.toString(myRobot.getTurtleID()));
        myCoordinatesLabel.setText(buildCoordinateString(myRobot.getCurrentCoordinate().getX(),
                                                         myRobot.getCurrentCoordinate().getY()));
        myBearingLabel.setText("Angle: " + myRobot.getCurrentRotation() + " deg");

        if (myRobot.isVisible())
            myVisibilityGroup.selectToggle(myVisibilityButton);
        else
            myVisibilityGroup.selectToggle(myInvisibilityButton);

        myRobotImage.setImage(new Image(myImageMap.getFile(0).toURI().toString()));
    }

    private String buildCoordinateString (Number aX, Number aY) {
        return "Coord: (" + aX + ", " + aY + ")";
    }

    @Override
    public int getRobotID () {
        // TODO Auto-generated method stub
        return myRobot.getTurtleID();
    }

    @Override
    public void onPenPress (EventHandler<MouseEvent> aEvent) {
        myPenFunctions.setOnMouseClicked(e -> popupPenSelector());
    }
    
    private void popupPenSelector () {
        Stage stage = new Stage();
        myPenPopup.initPopup(myImageMap);
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
