package front_end.view_modules.turtleBox;

import back_end.model.robot.IViewableRobot;
import front_end.view_modules.image_color_module.interfaces.IImageColorModule;
import front_end.view_modules.turtleBox.turtleMovement.TurtleMovement;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * 
 * @author George Bernard
 * @author Kayla Schulz
 *
 */
class ConcreteTurtleBox implements ITurtleBox {

    ScrollPane myScroller;
    Pane mySandbox;
    ColorPicker myBackgroundColorPicker;
    
    IImageColorModule myShapeColorMap;
    
    IViewableRobot myRobot;
    ImageView myImage;
    Group root;
    Canvas drawingCanvas;
    GraphicsContext gc;
    private int myWidth;
    private int myHeight;
    TurtleMovement myTurtMove;
    private boolean turtDisplayed;

    private final int CHARACTER_SIZE = 50;

    ConcreteTurtleBox (int width, int height, IImageColorModule aShapeColorMap) {
        myWidth = width;
        myHeight = height;
        myShapeColorMap = aShapeColorMap;
        
        root = new Group();
        drawingCanvas = new Canvas(width, height);
        gc = drawingCanvas.getGraphicsContext2D();
        myScroller = new ScrollPane();
        myScroller.setPrefSize(width, height);
        myScroller.setMinSize(width, height);
        myScroller.setMaxSize(width, height);

        mySandbox = new Pane();
        mySandbox.setPrefSize(width, height);
        mySandbox.setMinSize(width, height);
        mySandbox.setMaxSize(width, height);

        myScroller.setContent(mySandbox);
        mySandbox.getChildren().add(drawingCanvas);
        initBox(width, height);

    }

    public GraphicsContext getGC () {
        return gc;
    }

    public ImageView getTurtle () {
        return myImage;
    }
    
    public double getTurtleHeight() {
        return myImage.getFitHeight();
    }
    
    public double getTurtleWidth() {
        return myImage.getFitWidth();
    }

    @Override
    public void initBox (int aWidth, int aHeight) {
        initColorPicker();
        loadDefaultTurtle(aWidth, aHeight);
    }

    public void loadDefaultTurtle (int width, int height) {
        Image character = new Image( myShapeColorMap.getFile(0).toURI().toString() );
        myImage = new ImageView(character);
        myImage.setFitHeight(CHARACTER_SIZE);
        myImage.setFitWidth(CHARACTER_SIZE);
        myImage.setX(width / 2);
        myImage.setY(height / 2);
        turtDisplayed = true;
        mySandbox.getChildren().add(myImage);
    }

    @Override
    public void reset () {
        gc.clearRect(0, 0, myWidth, myHeight);
        removeTurtle();
        mySandbox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        initBox(myWidth, myHeight);
    }

    //TODO: Change this to reflect multiple turtles
    public void showTurtle () {
        if (!turtDisplayed) {
            mySandbox.getChildren().add(myImage);
        }
        turtDisplayed = true;
    }

    public void removeTurtle () {
        System.out.println("b");
        turtDisplayed = false;
        mySandbox.getChildren().remove(myImage);
    }

    @Override
    public Node getInstanceAsNode () {
        return myScroller;
    }

    @Override
    public void giveRobot (IViewableRobot aRobot) {
        myRobot = aRobot;
        
        myTurtMove = new TurtleMovement(this, myImage, getRobot(), myWidth, myHeight);
    }

    public IViewableRobot getRobot () {
        return myRobot;
    }

    @Override
    public void update () {
        if (myRobot == null) {
            return;
        }

        myTurtMove.update();
    }

    private void initColorPicker () {
        myBackgroundColorPicker = new ColorPicker();
        myBackgroundColorPicker.setOnAction(
                                            e -> mySandbox
                                                    .setBackground(new Background(new BackgroundFill(
                                                                                                     myBackgroundColorPicker
                                                                                                             .getValue(),
                                                                                                     CornerRadii.EMPTY,
                                                                                                     Insets.EMPTY))));
        mySandbox.getChildren().add(myBackgroundColorPicker);
    }
}
