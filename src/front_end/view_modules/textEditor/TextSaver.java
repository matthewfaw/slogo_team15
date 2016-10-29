package front_end.view_modules.textEditor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TextSaver {

	private ITextEditor myTextEditor;
	
	TextSaver(ITextEditor aTextEditor){
		myTextEditor = aTextEditor;
	}

	public void saveToFile(){
		FileChooser chooseFile = new FileChooser();
		chooseFile.setTitle("Save Text to .TXT File");
		File textFile = chooseFile.showSaveDialog(new Stage());
		if(textFile != null){
			try {
				Files.write(textFile.toPath(), 
							myTextEditor.getInstructionList(), 
							Charset.defaultCharset());
			} catch (IOException e) {
				saveToFile();
			}	
		}
	}
	
}

