package front_end.view_modules.penProperties;

import front_end.view_modules.image_color_module.interfaces.IColorModule;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;


public interface IPenPopup {
    
    public Scene getScene();
    
    public void clear();
    
    public void onApplyPress(EventHandler<MouseEvent> aEvent);
    
    public void onClearPress(EventHandler<MouseEvent> aEvent);

}
