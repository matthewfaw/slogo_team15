package front_end.view_modules.helpPage;

import integration.languages.Languages;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class HelpPage implements IHelpPage {
    
    private WebView myView;
    
    public WebView getMyView () {
        return myView;
    }

    public void loadHelpPage() {
        myView = new WebView();
        WebEngine webEngine = myView.getEngine();
        //TODO: Change this file load to less hard coded approach
        webEngine.load("file:///Users/kaylauser/Documents/workspace_fall16/slogo_team15/src/front_end/view_modules/helpPage/help_page.html");
    }

    @Override
    public void reset () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Node getInstanceAsNode () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void switchLanguage (Languages aLanguage) {
        // TODO Auto-generated method stub
        
    }

}