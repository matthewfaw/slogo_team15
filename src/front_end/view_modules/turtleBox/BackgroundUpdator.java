package front_end.view_modules.turtleBox;


import back_end.model.states.background.IViewableBackground;
import integration.observe.IObserver;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

class BackgroundUpdator implements IObserver {

	private IViewableBackground myObservableBackground;
	private Background myBG;
	private ColorPicker myColorPicker; 
	private BackgroundFill myFill;
	private Pane myPane;
	
	
	BackgroundUpdator(IViewableBackground aViewBackground, Pane aPane){
		myObservableBackground = aViewBackground;
		myObservableBackground.registerObserver(this);
		myColorPicker = new ColorPicker();
		myFill = new BackgroundFill( Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY);
		myBG = new Background(myFill);
		myPane = aPane;
	}
	
	ColorPicker getColorPicker(){
		return myColorPicker;
	}
	
	Background getBackground(){
		return myBG;
	}
	
	@Override
	public void update() {
		myColorPicker.setValue( Color.web(myObservableBackground.getBackgroundColor()) );
		myPane.setBackground(new Background(new BackgroundFill(myColorPicker.getValue(), CornerRadii.EMPTY, Insets.EMPTY)));
	}

}
