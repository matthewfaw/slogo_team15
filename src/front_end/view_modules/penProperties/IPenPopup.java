package front_end.view_modules.penProperties;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;


public interface IPenPopup {

    public void initPopup ();
    
    public Scene getScene();
    
    public void clear();
    
    public void onApplyPress(EventHandler<MouseEvent> aEvent);
    
    public void onClearPress(EventHandler<MouseEvent> aEvent);

}
