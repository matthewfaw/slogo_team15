package front_end.view_modules.turtleBox;

import back_end.model.robot.IViewRobot;
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


class ConcreteTurtleBox implements ITurtleBox {

    ScrollPane myScroller;
    Pane mySandbox;
    ColorPicker myBackgroundColorPicker;
    IViewRobot myRobot;
    ImageView myTurtle;
    Group root;
    Canvas drawingCanvas;
    GraphicsContext gc;
    private int myWidth;
    private int myHeight;
    TurtleMovement myTurtMove;
    private boolean turtDisplayed;

    private final int CHARACTER_SIZE = 50;

    ConcreteTurtleBox (int width, int height) {
        myWidth = width;
        myHeight = height;
        myTurtMove = new TurtleMovement(this, myWidth, myHeight);
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
        return myTurtle;
    }
    
    public double getTurtleHeight() {
        return myTurtle.getFitHeight();
    }
    
    public double getTurtleWidth() {
        return myTurtle.getFitWidth();
    }

    @Override
    public void initBox (int aWidth, int aHeight) {
        initColorPicker();
        loadDefaultTurtle(aWidth, aHeight);
    }

    public void loadDefaultTurtle (int width, int height) {
        Image character = new Image("turtle.png");
        myTurtle = new ImageView(character);
        myTurtle.setFitHeight(CHARACTER_SIZE);
        myTurtle.setFitWidth(CHARACTER_SIZE);
        myTurtle.setX(width / 2);
        myTurtle.setY(height / 2);
        turtDisplayed = true;
        mySandbox.getChildren().add(myTurtle);
    }

    @Override
    public void reset () {
        gc.clearRect(0, 0, myWidth, myHeight);
        System.out.println("a");
        removeTurtle();
        mySandbox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        initBox(myWidth, myHeight);
    }

    //TODO: Change this to reflect multiple turtles
    public void showTurtle () {
        if (!turtDisplayed) {
            mySandbox.getChildren().add(myTurtle);
        }
        turtDisplayed = true;
    }

    public void removeTurtle () {
        System.out.println("b");
        turtDisplayed = false;
        mySandbox.getChildren().remove(myTurtle);
    }

    @Override
    public Node getInstanceAsNode () {
        return myScroller;
    }

    @Override
    public void giveRobot (IViewRobot aRobot) {
        myRobot = aRobot;

    }

    public IViewRobot getRobot () {
        return myRobot;
    }

    @Override
    public void update () {
        if (myRobot == null) {
            return;
        }

        myTurtMove.updateTurtle();
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
