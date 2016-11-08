package front_end.view_modules.helpPage;

import java.net.URL;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


/**
 * This class initializes the web engine and web view for the help page (in html format).
 * It simply returns the help page and loads to web engine
 * 
 * @author Kayla Schulz
 *
 */
public class HelpPage implements IHelpPage {

    private WebView myView;

    public WebView getMyView () {
        return myView;
    }

    public void loadHelpPage () {
        myView = new WebView();
        WebEngine webEngine = myView.getEngine();
        URL url = this.getClass().getResource("help_page.html");
        webEngine.load(url.toString());
    }

    @Override
    public void reset () {
        // TODO Auto-generated method stub

    }

    @Override
    public Node getInstanceAsNode () {
        return myView;
    }

}
