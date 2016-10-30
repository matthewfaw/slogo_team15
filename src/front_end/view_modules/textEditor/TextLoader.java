package front_end.view_modules.textEditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

class TextLoader {

	private ITextEditor myTextEditor;
	private File myFile;
	private Scanner myScanner;
	
	TextLoader(ITextEditor aTextEditor){
		myTextEditor = aTextEditor;
	}
	
	void loadFile(){
		selectFile("Text Files: ");
		ArrayList<String> instructionList = new ArrayList<>();
		
		if(myScanner == null) return;
		
		while(myScanner.hasNext()) 
			instructionList.add(myScanner.nextLine());
		
		myScanner.close();
		myTextEditor.setInstructionList(instructionList);
	}
	
	private void openFileChooser(FileChooser chooseFile){
		myFile = chooseFile.showOpenDialog(new Stage());
		if(myFile != null){
			initScanner(myFile);
		}
	}
	
	private void selectFile( String aFieldText ) {
		FileChooser choose = new FileChooser();
		choose.setTitle("Load .TXT File to Editor");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter( aFieldText, "*.txt");
		choose.getExtensionFilters().add(extFilter);
		openFileChooser( choose );
	}
	
	
	private void initScanner(File aFile){
		try {
			myScanner = new Scanner(aFile);
		} catch (FileNotFoundException e) {
			selectFile("Choose a real TXT file this time: ");
		}
	}	
}