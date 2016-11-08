package front_end.view_modules.turtleBox;

import back_end.model.robot.IViewableRobot;
import back_end.model.states.background.IViewableBackground;
import front_end.sender.IColorSender;
import front_end.view_modules.image_color_module.interfaces.IImageColorModule;
import front_end.view_modules.turtleBox.turtleMovement.TurtleDrawer;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;


/**
 * This class creates the concrete implementation of the turtle box. It implements the
 * turtle box interface and sets the specifics for how the turtle box will be laid out.
 * @author George Bernard
 * @author Kayla Schulz
 *
 */
class ConcreteTurtleBox implements ITurtleBox {
    ScrollPane myScroller;
    Pane mySandbox;
    ColorPicker myBackgroundColorPicker;

    BackgroundUpdator myBU;
    TurtleBoxToolbar myBoxToolbar;

    IImageColorModule myShapeColorMap;

    IViewableRobot myRobot;
    ImageView myImage;
    Group root;
    private int myWidth;
    private int myHeight;

    TurtleDrawer myTurtleDrawer;
	private IColorSender myColorSender;

    ConcreteTurtleBox (int width, int height, IImageColorModule aShapeColorMap) {
        myWidth = width;
        myHeight = height;
        myShapeColorMap = aShapeColorMap;

        root = new Group();
        myScroller = new ScrollPane();
        myScroller.setPrefSize(width, height);
        myScroller.setMinSize(width, height);
        myScroller.setMaxSize(width, height);
        mySandbox = new Pane();
        mySandbox.setPrefSize(width, height);
        mySandbox.setMinSize(width, height);
        mySandbox.setMaxSize(width, height);
        myScroller.setContent(mySandbox);
        //Probably need to move this
        myBackgroundColorPicker = new ColorPicker();
        myBoxToolbar = new TurtleBoxToolbar(myBackgroundColorPicker);
    }

    @Override
    public void reset () {
        mySandbox
                .setBackground(new Background(new BackgroundFill(myBackgroundColorPicker.getValue(),
                                                                 CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @Override
    public Node getInstanceAsNode () {
        return myScroller;
    }

    @Override
    public void giveRobot (IViewableRobot aRobot) {
        myRobot = aRobot;
        myTurtleDrawer = new TurtleDrawer(aRobot, myShapeColorMap, myWidth, myHeight, myBoxToolbar);
        mySandbox.getChildren().addAll(myTurtleDrawer.getCanvas(), myTurtleDrawer.getImage());
    }

    @Override
    public void giveBackground (IViewableBackground aViewBackground) {
        myBU = new BackgroundUpdator(aViewBackground, mySandbox);
        mySandbox.setBackground(myBU.getBackground());
        myBackgroundColorPicker = myBU.getColorPicker();
        //mySandbox.getChildren().add(myBackgroundColorPicker);
        mySandbox.getChildren().add(myBoxToolbar.createBoxToolbar());
    }

	@Override
	public void giveColorSender(IColorSender aColorSender){
		myColorSender = aColorSender;
	}
}
