package front_end.appScene.textEditor;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


class TextEditorToolbar {
    HBox myBar;
    ConcreteTextEditor myTextEditor;
	private ResourceBundle myTextToolbarResources;

	private static final String RESET_KEY = "ResetButton";
	private static final String CLEAR_TEXT_KEY = "ClearButton";
	private static final String CLEAR_HIGHLIGHT_KEY = "ClearHighlightButton";
	private static final String LOAD_TEXT_KEY = "LoadTextButton";
	private static final String SAVE_TEXT_KEY = "SaveTextButton";
	
	private static final String INIT_FILE  = "resources.frontend.";
	private static final String FILENAME   = "EnglishTextEditorToolbar"; 
    
    TextEditorToolbar (ConcreteTextEditor aTextEditor) {
        myTextEditor = aTextEditor;
        myBar = new HBox(0);
		myTextToolbarResources = ResourceBundle.getBundle(INIT_FILE + FILENAME);
        initButtons();
    }

    /**
     * getter for text editor toolbar
     * 
     * @return text editor toolbar
     */
    public Node getBar () {
        return myBar;
    }

    private void initButtons () {
        initResetButton();
        initClearTextButton();
        initClearHighlightButton();
        initLoadTextButton();
        initSaveTextButton();
    }

    private void initResetButton () {
        Button reset = new Button(myTextToolbarResources.getString(RESET_KEY));
        reset.setOnMouseClicked(e -> myTextEditor.reset());
        myBar.getChildren().add(reset);
    }

    private void initClearTextButton () {
        Button clearText = new Button(myTextToolbarResources.getString(CLEAR_TEXT_KEY));
        clearText.setOnMouseClicked(e -> myTextEditor.clearTextFields());
        myBar.getChildren().add(clearText);
    }
    
    private void initClearHighlightButton(){
		String key = "ClearHighlightButton";
		Button clearHighlight = new Button(myTextToolbarResources.getString(CLEAR_HIGHLIGHT_KEY));
		clearHighlight.setOnMouseClicked(e -> {
			for(int i = 0; i < myTextEditor.getEditorSize(); i++){
				myTextEditor.highlightLine(Color.TRANSPARENT, i);
			}
		});
		myBar.getChildren().add(clearHighlight);
	}

    private void initLoadTextButton () {
        Button loadText = new Button(myTextToolbarResources.getString(LOAD_TEXT_KEY));
        myBar.getChildren().add(loadText);
        // TODO: Implement Loading Text from TXT file
    }

    private void initSaveTextButton () {
        Button saveText = new Button(myTextToolbarResources.getString(SAVE_TEXT_KEY));
        myBar.getChildren().add(saveText);
        // TODO: Implement Saving Text to TXT file
    }
}
