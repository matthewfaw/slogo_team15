
import front_end.applicationController.ApplicationController;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Main class for creating an animated scene.
 * 
 */
public class Main extends Application {

    private ApplicationController myApplicationController;
    private final int SIZE = 600;
    private final int WIDTH = 2 * SIZE;
    private final int HEIGHT =  SIZE;
    

    /**
     * Starts initializing the stage and scene
     * 
     * @return nothing
     */
    @Override
    public void start (Stage s) {
        myApplicationController = new ApplicationController(WIDTH, HEIGHT);
        s.setTitle(myApplicationController.getTitle());

        Scene scene = myApplicationController.getScene();
        s.setResizable(false);
        
        s.setScene(scene);
        s.show();
    }
   

    public static void main (String[] args) {
        launch(args);
    }

}
