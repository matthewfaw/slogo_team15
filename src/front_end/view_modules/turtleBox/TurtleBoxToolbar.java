package front_end.view_modules.turtleBox;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;


public class TurtleBoxToolbar {

    private Slider myAnimationSlider;

    private final int SPACING = 15;
    private static final double MIN_SLIDER_VALUE = 0.5;
    private static final double MAX_SLIDER_VALUE = 2.0;

    private ColorPicker myBackgroundColorPicker;

    public TurtleBoxToolbar (ColorPicker aBackgroundColorPicker) {
        myAnimationSlider = new Slider(MIN_SLIDER_VALUE, MAX_SLIDER_VALUE, 1);
        myBackgroundColorPicker = aBackgroundColorPicker;
    }

    protected HBox createBoxToolbar () {
        HBox myBox = new HBox(SPACING);
        Label myLabel = new Label("Animation Speed:");
        myBox.getChildren().addAll(myBackgroundColorPicker, myLabel, myAnimationSlider);
        return myBox;
    }

    public double getMyAnimationSliderValue () {
        return myAnimationSlider.getValue();
    }
    
    public static double getMaxSliderValue () {
        return MAX_SLIDER_VALUE;
    }
}
