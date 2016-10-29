package front_end.appScene.textEditor;

import java.util.List;
import front_end.appScene.IViewer;
import javafx.scene.paint.Color;


public interface ITextEditor extends IViewer {

    /**
     * Highlight a line in the text editor - will be used by either
     * step or error
     * 
     * @param color - the color to be used when highlighting the line
     * @param line - the line number to be highlighted
     */
    public void highlightLine (Color color, int line);

    /**
     * Get the instruction list from the text editor
     * This will occur on run clicked
     * 
     * @return instruction list
     */
    public List<String> getInstructionList ();

    /**
     * Set the instructions inside of the text editor
     * This will receive its input from the command box
     * 
     * @param list of instructions
     */
    public void setInstructionList (List<String> instructions);
}