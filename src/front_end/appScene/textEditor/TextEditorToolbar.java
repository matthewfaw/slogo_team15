package front_end.appScene.textEditor;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

class TextEditorToolbar {
	HBox myBar;
	ConcreteTextEditor myTextEditor;
	
	TextEditorToolbar(ConcreteTextEditor aTextEditor){
		myTextEditor = aTextEditor;
		myBar = new HBox(0);
		initButtons();
	}
	
	public Node getBar(){
		return myBar;
	}
	
	private void initButtons(){
		initResetButton();
		initClearTextButton();
		initLoadTextButton();
		initSaveTextButton();
	}
	
	private void initResetButton(){
		Button reset = new Button("Reset");
		reset.setOnMouseClicked(e -> myTextEditor.reset());
		myBar.getChildren().add(reset);
	}
	
	private void initClearTextButton(){
		Button clearText = new Button("Clear Text");
		clearText.setOnMouseClicked(e -> myTextEditor.clearTextFields() ); 
		myBar.getChildren().add(clearText);
	}
	
	private void initLoadTextButton(){
		Button loadText = new Button("Load Text");
		myBar.getChildren().add(loadText);
		// TODO: Implement Loading Text from TXT file
	}
	
	private void initSaveTextButton(){
		Button saveText = new Button("Save Text");
		myBar.getChildren().add(saveText);
		// TODO: Implement Saving Text to TXT file
	}
}
